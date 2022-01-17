import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IContentTag, getContentTagIdentifier } from '../content-tag.model';

export type EntityResponseType = HttpResponse<IContentTag>;
export type EntityArrayResponseType = HttpResponse<IContentTag[]>;

@Injectable({ providedIn: 'root' })
export class ContentTagService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/content-tags');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(contentTag: IContentTag): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contentTag);
    return this.http
      .post<IContentTag>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(contentTag: IContentTag): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contentTag);
    return this.http
      .put<IContentTag>(`${this.resourceUrl}/${getContentTagIdentifier(contentTag) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(contentTag: IContentTag): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contentTag);
    return this.http
      .patch<IContentTag>(`${this.resourceUrl}/${getContentTagIdentifier(contentTag) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IContentTag>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IContentTag[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addContentTagToCollectionIfMissing(
    contentTagCollection: IContentTag[],
    ...contentTagsToCheck: (IContentTag | null | undefined)[]
  ): IContentTag[] {
    const contentTags: IContentTag[] = contentTagsToCheck.filter(isPresent);
    if (contentTags.length > 0) {
      const contentTagCollectionIdentifiers = contentTagCollection.map(contentTagItem => getContentTagIdentifier(contentTagItem)!);
      const contentTagsToAdd = contentTags.filter(contentTagItem => {
        const contentTagIdentifier = getContentTagIdentifier(contentTagItem);
        if (contentTagIdentifier == null || contentTagCollectionIdentifiers.includes(contentTagIdentifier)) {
          return false;
        }
        contentTagCollectionIdentifiers.push(contentTagIdentifier);
        return true;
      });
      return [...contentTagsToAdd, ...contentTagCollection];
    }
    return contentTagCollection;
  }

  protected convertDateFromClient(contentTag: IContentTag): IContentTag {
    return Object.assign({}, contentTag, {
      createdDate: contentTag.createdDate?.isValid() ? contentTag.createdDate.toJSON() : undefined,
      lastModifiedDate: contentTag.lastModifiedDate?.isValid() ? contentTag.lastModifiedDate.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? dayjs(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? dayjs(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((contentTag: IContentTag) => {
        contentTag.createdDate = contentTag.createdDate ? dayjs(contentTag.createdDate) : undefined;
        contentTag.lastModifiedDate = contentTag.lastModifiedDate ? dayjs(contentTag.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
