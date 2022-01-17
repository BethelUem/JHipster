import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICatalog, getCatalogIdentifier } from '../catalog.model';

export type EntityResponseType = HttpResponse<ICatalog>;
export type EntityArrayResponseType = HttpResponse<ICatalog[]>;

@Injectable({ providedIn: 'root' })
export class CatalogService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/catalogs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(catalog: ICatalog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(catalog);
    return this.http
      .post<ICatalog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(catalog: ICatalog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(catalog);
    return this.http
      .put<ICatalog>(`${this.resourceUrl}/${getCatalogIdentifier(catalog) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(catalog: ICatalog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(catalog);
    return this.http
      .patch<ICatalog>(`${this.resourceUrl}/${getCatalogIdentifier(catalog) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICatalog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICatalog[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addCatalogToCollectionIfMissing(catalogCollection: ICatalog[], ...catalogsToCheck: (ICatalog | null | undefined)[]): ICatalog[] {
    const catalogs: ICatalog[] = catalogsToCheck.filter(isPresent);
    if (catalogs.length > 0) {
      const catalogCollectionIdentifiers = catalogCollection.map(catalogItem => getCatalogIdentifier(catalogItem)!);
      const catalogsToAdd = catalogs.filter(catalogItem => {
        const catalogIdentifier = getCatalogIdentifier(catalogItem);
        if (catalogIdentifier == null || catalogCollectionIdentifiers.includes(catalogIdentifier)) {
          return false;
        }
        catalogCollectionIdentifiers.push(catalogIdentifier);
        return true;
      });
      return [...catalogsToAdd, ...catalogCollection];
    }
    return catalogCollection;
  }

  protected convertDateFromClient(catalog: ICatalog): ICatalog {
    return Object.assign({}, catalog, {
      createdDate: catalog.createdDate?.isValid() ? catalog.createdDate.toJSON() : undefined,
      lastModifiedDate: catalog.lastModifiedDate?.isValid() ? catalog.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((catalog: ICatalog) => {
        catalog.createdDate = catalog.createdDate ? dayjs(catalog.createdDate) : undefined;
        catalog.lastModifiedDate = catalog.lastModifiedDate ? dayjs(catalog.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
