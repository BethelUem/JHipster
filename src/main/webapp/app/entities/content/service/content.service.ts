import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IContent, getContentIdentifier } from '../content.model';

export type EntityResponseType = HttpResponse<IContent>;
export type EntityArrayResponseType = HttpResponse<IContent[]>;

@Injectable({ providedIn: 'root' })
export class ContentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/contents');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(content: IContent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(content);
    return this.http
      .post<IContent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(content: IContent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(content);
    return this.http
      .put<IContent>(`${this.resourceUrl}/${getContentIdentifier(content) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(content: IContent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(content);
    return this.http
      .patch<IContent>(`${this.resourceUrl}/${getContentIdentifier(content) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IContent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IContent[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addContentToCollectionIfMissing(contentCollection: IContent[], ...contentsToCheck: (IContent | null | undefined)[]): IContent[] {
    const contents: IContent[] = contentsToCheck.filter(isPresent);
    if (contents.length > 0) {
      const contentCollectionIdentifiers = contentCollection.map(contentItem => getContentIdentifier(contentItem)!);
      const contentsToAdd = contents.filter(contentItem => {
        const contentIdentifier = getContentIdentifier(contentItem);
        if (contentIdentifier == null || contentCollectionIdentifiers.includes(contentIdentifier)) {
          return false;
        }
        contentCollectionIdentifiers.push(contentIdentifier);
        return true;
      });
      return [...contentsToAdd, ...contentCollection];
    }
    return contentCollection;
  }

  protected convertDateFromClient(content: IContent): IContent {
    return Object.assign({}, content, {
      recordingDate: content.recordingDate?.isValid() ? content.recordingDate.toJSON() : undefined,
      editionDate: content.editionDate?.isValid() ? content.editionDate.toJSON() : undefined,
      createdDate: content.createdDate?.isValid() ? content.createdDate.toJSON() : undefined,
      lastModifiedDate: content.lastModifiedDate?.isValid() ? content.lastModifiedDate.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.recordingDate = res.body.recordingDate ? dayjs(res.body.recordingDate) : undefined;
      res.body.editionDate = res.body.editionDate ? dayjs(res.body.editionDate) : undefined;
      res.body.createdDate = res.body.createdDate ? dayjs(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? dayjs(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((content: IContent) => {
        content.recordingDate = content.recordingDate ? dayjs(content.recordingDate) : undefined;
        content.editionDate = content.editionDate ? dayjs(content.editionDate) : undefined;
        content.createdDate = content.createdDate ? dayjs(content.createdDate) : undefined;
        content.lastModifiedDate = content.lastModifiedDate ? dayjs(content.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
