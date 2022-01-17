import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserProgram, getUserProgramIdentifier } from '../user-program.model';

export type EntityResponseType = HttpResponse<IUserProgram>;
export type EntityArrayResponseType = HttpResponse<IUserProgram[]>;

@Injectable({ providedIn: 'root' })
export class UserProgramService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/user-programs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(userProgram: IUserProgram): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userProgram);
    return this.http
      .post<IUserProgram>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userProgram: IUserProgram): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userProgram);
    return this.http
      .put<IUserProgram>(`${this.resourceUrl}/${getUserProgramIdentifier(userProgram) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(userProgram: IUserProgram): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userProgram);
    return this.http
      .patch<IUserProgram>(`${this.resourceUrl}/${getUserProgramIdentifier(userProgram) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserProgram>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserProgram[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addUserProgramToCollectionIfMissing(
    userProgramCollection: IUserProgram[],
    ...userProgramsToCheck: (IUserProgram | null | undefined)[]
  ): IUserProgram[] {
    const userPrograms: IUserProgram[] = userProgramsToCheck.filter(isPresent);
    if (userPrograms.length > 0) {
      const userProgramCollectionIdentifiers = userProgramCollection.map(userProgramItem => getUserProgramIdentifier(userProgramItem)!);
      const userProgramsToAdd = userPrograms.filter(userProgramItem => {
        const userProgramIdentifier = getUserProgramIdentifier(userProgramItem);
        if (userProgramIdentifier == null || userProgramCollectionIdentifiers.includes(userProgramIdentifier)) {
          return false;
        }
        userProgramCollectionIdentifiers.push(userProgramIdentifier);
        return true;
      });
      return [...userProgramsToAdd, ...userProgramCollection];
    }
    return userProgramCollection;
  }

  protected convertDateFromClient(userProgram: IUserProgram): IUserProgram {
    return Object.assign({}, userProgram, {
      createdDate: userProgram.createdDate?.isValid() ? userProgram.createdDate.toJSON() : undefined,
      lastModifiedDate: userProgram.lastModifiedDate?.isValid() ? userProgram.lastModifiedDate.toJSON() : undefined,
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
      res.body.forEach((userProgram: IUserProgram) => {
        userProgram.createdDate = userProgram.createdDate ? dayjs(userProgram.createdDate) : undefined;
        userProgram.lastModifiedDate = userProgram.lastModifiedDate ? dayjs(userProgram.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
