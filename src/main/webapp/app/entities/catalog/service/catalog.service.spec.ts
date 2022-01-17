import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICatalog, Catalog } from '../catalog.model';

import { CatalogService } from './catalog.service';

describe('Catalog Service', () => {
  let service: CatalogService;
  let httpMock: HttpTestingController;
  let elemDefault: ICatalog;
  let expectedResult: ICatalog | ICatalog[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CatalogService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      code: 'AAAAAAA',
      name: 'AAAAAAA',
      description: 'AAAAAAA',
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

    it('should create a Catalog', () => {
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

      service.create(new Catalog()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Catalog', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          code: 'BBBBBB',
          name: 'BBBBBB',
          description: 'BBBBBB',
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

    it('should partial update a Catalog', () => {
      const patchObject = Object.assign(
        {
          code: 'BBBBBB',
          name: 'BBBBBB',
          active: true,
          createById: 1,
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        new Catalog()
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

    it('should return a list of Catalog', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          code: 'BBBBBB',
          name: 'BBBBBB',
          description: 'BBBBBB',
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

    it('should delete a Catalog', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addCatalogToCollectionIfMissing', () => {
      it('should add a Catalog to an empty array', () => {
        const catalog: ICatalog = { id: 123 };
        expectedResult = service.addCatalogToCollectionIfMissing([], catalog);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(catalog);
      });

      it('should not add a Catalog to an array that contains it', () => {
        const catalog: ICatalog = { id: 123 };
        const catalogCollection: ICatalog[] = [
          {
            ...catalog,
          },
          { id: 456 },
        ];
        expectedResult = service.addCatalogToCollectionIfMissing(catalogCollection, catalog);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Catalog to an array that doesn't contain it", () => {
        const catalog: ICatalog = { id: 123 };
        const catalogCollection: ICatalog[] = [{ id: 456 }];
        expectedResult = service.addCatalogToCollectionIfMissing(catalogCollection, catalog);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(catalog);
      });

      it('should add only unique Catalog to an array', () => {
        const catalogArray: ICatalog[] = [{ id: 123 }, { id: 456 }, { id: 59880 }];
        const catalogCollection: ICatalog[] = [{ id: 123 }];
        expectedResult = service.addCatalogToCollectionIfMissing(catalogCollection, ...catalogArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const catalog: ICatalog = { id: 123 };
        const catalog2: ICatalog = { id: 456 };
        expectedResult = service.addCatalogToCollectionIfMissing([], catalog, catalog2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(catalog);
        expect(expectedResult).toContain(catalog2);
      });

      it('should accept null and undefined values', () => {
        const catalog: ICatalog = { id: 123 };
        expectedResult = service.addCatalogToCollectionIfMissing([], null, catalog, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(catalog);
      });

      it('should return initial array if no Catalog is added', () => {
        const catalogCollection: ICatalog[] = [{ id: 123 }];
        expectedResult = service.addCatalogToCollectionIfMissing(catalogCollection, undefined, null);
        expect(expectedResult).toEqual(catalogCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
