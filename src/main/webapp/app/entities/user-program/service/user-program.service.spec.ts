import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IUserProgram, UserProgram } from '../user-program.model';

import { UserProgramService } from './user-program.service';

describe('UserProgram Service', () => {
  let service: UserProgramService;
  let httpMock: HttpTestingController;
  let elemDefault: IUserProgram;
  let expectedResult: IUserProgram | IUserProgram[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UserProgramService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      user: 0,
      active: false,
      createById: 0,
      createdDate: currentDate,
      lastModifiedById: 0,
      lastModifiedDate: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a UserProgram', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          createdDate: currentDate,
          lastModifiedDate: currentDate,
        },
        returnedFromService
      );

      service.create(new UserProgram()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a UserProgram', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          user: 1,
          active: true,
          createById: 1,
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedById: 1,
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          createdDate: currentDate,
          lastModifiedDate: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a UserProgram', () => {
      const patchObject = Object.assign(
        {
          active: true,
          createById: 1,
          lastModifiedById: 1,
        },
        new UserProgram()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          createdDate: currentDate,
          lastModifiedDate: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of UserProgram', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          user: 1,
          active: true,
          createById: 1,
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedById: 1,
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          createdDate: currentDate,
          lastModifiedDate: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a UserProgram', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addUserProgramToCollectionIfMissing', () => {
      it('should add a UserProgram to an empty array', () => {
        const userProgram: IUserProgram = { id: 123 };
        expectedResult = service.addUserProgramToCollectionIfMissing([], userProgram);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userProgram);
      });

      it('should not add a UserProgram to an array that contains it', () => {
        const userProgram: IUserProgram = { id: 123 };
        const userProgramCollection: IUserProgram[] = [
          {
            ...userProgram,
          },
          { id: 456 },
        ];
        expectedResult = service.addUserProgramToCollectionIfMissing(userProgramCollection, userProgram);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a UserProgram to an array that doesn't contain it", () => {
        const userProgram: IUserProgram = { id: 123 };
        const userProgramCollection: IUserProgram[] = [{ id: 456 }];
        expectedResult = service.addUserProgramToCollectionIfMissing(userProgramCollection, userProgram);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userProgram);
      });

      it('should add only unique UserProgram to an array', () => {
        const userProgramArray: IUserProgram[] = [{ id: 123 }, { id: 456 }, { id: 77078 }];
        const userProgramCollection: IUserProgram[] = [{ id: 123 }];
        expectedResult = service.addUserProgramToCollectionIfMissing(userProgramCollection, ...userProgramArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const userProgram: IUserProgram = { id: 123 };
        const userProgram2: IUserProgram = { id: 456 };
        expectedResult = service.addUserProgramToCollectionIfMissing([], userProgram, userProgram2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userProgram);
        expect(expectedResult).toContain(userProgram2);
      });

      it('should accept null and undefined values', () => {
        const userProgram: IUserProgram = { id: 123 };
        expectedResult = service.addUserProgramToCollectionIfMissing([], null, userProgram, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userProgram);
      });

      it('should return initial array if no UserProgram is added', () => {
        const userProgramCollection: IUserProgram[] = [{ id: 123 }];
        expectedResult = service.addUserProgramToCollectionIfMissing(userProgramCollection, undefined, null);
        expect(expectedResult).toEqual(userProgramCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
