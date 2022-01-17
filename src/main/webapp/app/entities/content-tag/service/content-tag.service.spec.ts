import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IContentTag, ContentTag } from '../content-tag.model';

import { ContentTagService } from './content-tag.service';

describe('ContentTag Service', () => {
  let service: ContentTagService;
  let httpMock: HttpTestingController;
  let elemDefault: IContentTag;
  let expectedResult: IContentTag | IContentTag[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ContentTagService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
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

    it('should create a ContentTag', () => {
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

      service.create(new ContentTag()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ContentTag', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
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

    it('should partial update a ContentTag', () => {
      const patchObject = Object.assign(
        {
          lastModifiedById: 1,
        },
        new ContentTag()
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

    it('should return a list of ContentTag', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
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

    it('should delete a ContentTag', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addContentTagToCollectionIfMissing', () => {
      it('should add a ContentTag to an empty array', () => {
        const contentTag: IContentTag = { id: 123 };
        expectedResult = service.addContentTagToCollectionIfMissing([], contentTag);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(contentTag);
      });

      it('should not add a ContentTag to an array that contains it', () => {
        const contentTag: IContentTag = { id: 123 };
        const contentTagCollection: IContentTag[] = [
          {
            ...contentTag,
          },
          { id: 456 },
        ];
        expectedResult = service.addContentTagToCollectionIfMissing(contentTagCollection, contentTag);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ContentTag to an array that doesn't contain it", () => {
        const contentTag: IContentTag = { id: 123 };
        const contentTagCollection: IContentTag[] = [{ id: 456 }];
        expectedResult = service.addContentTagToCollectionIfMissing(contentTagCollection, contentTag);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(contentTag);
      });

      it('should add only unique ContentTag to an array', () => {
        const contentTagArray: IContentTag[] = [{ id: 123 }, { id: 456 }, { id: 19646 }];
        const contentTagCollection: IContentTag[] = [{ id: 123 }];
        expectedResult = service.addContentTagToCollectionIfMissing(contentTagCollection, ...contentTagArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const contentTag: IContentTag = { id: 123 };
        const contentTag2: IContentTag = { id: 456 };
        expectedResult = service.addContentTagToCollectionIfMissing([], contentTag, contentTag2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(contentTag);
        expect(expectedResult).toContain(contentTag2);
      });

      it('should accept null and undefined values', () => {
        const contentTag: IContentTag = { id: 123 };
        expectedResult = service.addContentTagToCollectionIfMissing([], null, contentTag, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(contentTag);
      });

      it('should return initial array if no ContentTag is added', () => {
        const contentTagCollection: IContentTag[] = [{ id: 123 }];
        expectedResult = service.addContentTagToCollectionIfMissing(contentTagCollection, undefined, null);
        expect(expectedResult).toEqual(contentTagCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
