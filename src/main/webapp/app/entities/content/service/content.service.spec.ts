import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IContent, Content } from '../content.model';

import { ContentService } from './content.service';

describe('Content Service', () => {
  let service: ContentService;
  let httpMock: HttpTestingController;
  let elemDefault: IContent;
  let expectedResult: IContent | IContent[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ContentService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      recordingDate: currentDate,
      editionDate: currentDate,
      signatureOriginal: 'AAAAAAA',
      signatureEdited: 'AAAAAAA',
      eventReason: 'AAAAAAA',
      eventSlogan: 'AAAAAAA',
      nameConduction: 'AAAAAAA',
      positionConduction: 'AAAAAAA',
      specialtiesPanel: 'AAAAAAA',
      namePanel: 'AAAAAAA',
      titleSubject: 'AAAAAAA',
      biblicalPassage: 'AAAAAAA',
      synopsisDescription: 'AAAAAAA',
      storyline: 'AAAAAAA',
      mediaContent: 'AAAAAAA',
      durationOriginal: 'PT1S',
      durationEdited: 'PT1S',
      scenography: 'AAAAAAA',
      location: 'AAAAAAA',
      city: 'AAAAAAA',
      departmentState: 'AAAAAAA',
      producer: 'AAAAAAA',
      productionAssistant: 'AAAAAAA',
      operatorVTRPlayOut: 'AAAAAAA',
      productionCredits: 'AAAAAAA',
      castActors: 'AAAAAAA',
      archiveStatus: 'AAAAAAA',
      interpreterTranslator: 'AAAAAAA',
      dubbing: 'AAAAAAA',
      subtitleCC: 'AAAAAAA',
      cataloguerOriginal: 'AAAAAAA',
      cataloguerEdited: 'AAAAAAA',
      postproductionEditor: 'AAAAAAA',
      operatorIngestion: 'AAAAAAA',
      serviceOTT: false,
      archivadoLTOOriginal: false,
      archivadoLTOEdited: false,
      observations: 'AAAAAAA',
      rightsManagement: 'AAAAAAA',
      cataloguerProduction: 'AAAAAAA',
      cataloguerIngest: 'AAAAAAA',
      cataloguerMaster: 'AAAAAAA',
      urlEdition: 'AAAAAAA',
      urlOriginal: 'AAAAAAA',
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
          recordingDate: currentDate.format(DATE_TIME_FORMAT),
          editionDate: currentDate.format(DATE_TIME_FORMAT),
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

    it('should create a Content', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          recordingDate: currentDate.format(DATE_TIME_FORMAT),
          editionDate: currentDate.format(DATE_TIME_FORMAT),
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          recordingDate: currentDate,
          editionDate: currentDate,
          createdDate: currentDate,
          lastModifiedDate: currentDate,
        },
        returnedFromService
      );

      service.create(new Content()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Content', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          recordingDate: currentDate.format(DATE_TIME_FORMAT),
          editionDate: currentDate.format(DATE_TIME_FORMAT),
          signatureOriginal: 'BBBBBB',
          signatureEdited: 'BBBBBB',
          eventReason: 'BBBBBB',
          eventSlogan: 'BBBBBB',
          nameConduction: 'BBBBBB',
          positionConduction: 'BBBBBB',
          specialtiesPanel: 'BBBBBB',
          namePanel: 'BBBBBB',
          titleSubject: 'BBBBBB',
          biblicalPassage: 'BBBBBB',
          synopsisDescription: 'BBBBBB',
          storyline: 'BBBBBB',
          mediaContent: 'BBBBBB',
          durationOriginal: 'BBBBBB',
          durationEdited: 'BBBBBB',
          scenography: 'BBBBBB',
          location: 'BBBBBB',
          city: 'BBBBBB',
          departmentState: 'BBBBBB',
          producer: 'BBBBBB',
          productionAssistant: 'BBBBBB',
          operatorVTRPlayOut: 'BBBBBB',
          productionCredits: 'BBBBBB',
          castActors: 'BBBBBB',
          archiveStatus: 'BBBBBB',
          interpreterTranslator: 'BBBBBB',
          dubbing: 'BBBBBB',
          subtitleCC: 'BBBBBB',
          cataloguerOriginal: 'BBBBBB',
          cataloguerEdited: 'BBBBBB',
          postproductionEditor: 'BBBBBB',
          operatorIngestion: 'BBBBBB',
          serviceOTT: true,
          archivadoLTOOriginal: true,
          archivadoLTOEdited: true,
          observations: 'BBBBBB',
          rightsManagement: 'BBBBBB',
          cataloguerProduction: 'BBBBBB',
          cataloguerIngest: 'BBBBBB',
          cataloguerMaster: 'BBBBBB',
          urlEdition: 'BBBBBB',
          urlOriginal: 'BBBBBB',
          createById: 1,
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedById: 1,
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          recordingDate: currentDate,
          editionDate: currentDate,
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

    it('should partial update a Content', () => {
      const patchObject = Object.assign(
        {
          recordingDate: currentDate.format(DATE_TIME_FORMAT),
          signatureOriginal: 'BBBBBB',
          signatureEdited: 'BBBBBB',
          specialtiesPanel: 'BBBBBB',
          durationOriginal: 'BBBBBB',
          scenography: 'BBBBBB',
          location: 'BBBBBB',
          interpreterTranslator: 'BBBBBB',
          subtitleCC: 'BBBBBB',
          archivadoLTOOriginal: true,
          archivadoLTOEdited: true,
          rightsManagement: 'BBBBBB',
          cataloguerProduction: 'BBBBBB',
          cataloguerIngest: 'BBBBBB',
          cataloguerMaster: 'BBBBBB',
          urlOriginal: 'BBBBBB',
          lastModifiedById: 1,
        },
        new Content()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          recordingDate: currentDate,
          editionDate: currentDate,
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

    it('should return a list of Content', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          recordingDate: currentDate.format(DATE_TIME_FORMAT),
          editionDate: currentDate.format(DATE_TIME_FORMAT),
          signatureOriginal: 'BBBBBB',
          signatureEdited: 'BBBBBB',
          eventReason: 'BBBBBB',
          eventSlogan: 'BBBBBB',
          nameConduction: 'BBBBBB',
          positionConduction: 'BBBBBB',
          specialtiesPanel: 'BBBBBB',
          namePanel: 'BBBBBB',
          titleSubject: 'BBBBBB',
          biblicalPassage: 'BBBBBB',
          synopsisDescription: 'BBBBBB',
          storyline: 'BBBBBB',
          mediaContent: 'BBBBBB',
          durationOriginal: 'BBBBBB',
          durationEdited: 'BBBBBB',
          scenography: 'BBBBBB',
          location: 'BBBBBB',
          city: 'BBBBBB',
          departmentState: 'BBBBBB',
          producer: 'BBBBBB',
          productionAssistant: 'BBBBBB',
          operatorVTRPlayOut: 'BBBBBB',
          productionCredits: 'BBBBBB',
          castActors: 'BBBBBB',
          archiveStatus: 'BBBBBB',
          interpreterTranslator: 'BBBBBB',
          dubbing: 'BBBBBB',
          subtitleCC: 'BBBBBB',
          cataloguerOriginal: 'BBBBBB',
          cataloguerEdited: 'BBBBBB',
          postproductionEditor: 'BBBBBB',
          operatorIngestion: 'BBBBBB',
          serviceOTT: true,
          archivadoLTOOriginal: true,
          archivadoLTOEdited: true,
          observations: 'BBBBBB',
          rightsManagement: 'BBBBBB',
          cataloguerProduction: 'BBBBBB',
          cataloguerIngest: 'BBBBBB',
          cataloguerMaster: 'BBBBBB',
          urlEdition: 'BBBBBB',
          urlOriginal: 'BBBBBB',
          createById: 1,
          createdDate: currentDate.format(DATE_TIME_FORMAT),
          lastModifiedById: 1,
          lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          recordingDate: currentDate,
          editionDate: currentDate,
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

    it('should delete a Content', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addContentToCollectionIfMissing', () => {
      it('should add a Content to an empty array', () => {
        const content: IContent = { id: 123 };
        expectedResult = service.addContentToCollectionIfMissing([], content);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(content);
      });

      it('should not add a Content to an array that contains it', () => {
        const content: IContent = { id: 123 };
        const contentCollection: IContent[] = [
          {
            ...content,
          },
          { id: 456 },
        ];
        expectedResult = service.addContentToCollectionIfMissing(contentCollection, content);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Content to an array that doesn't contain it", () => {
        const content: IContent = { id: 123 };
        const contentCollection: IContent[] = [{ id: 456 }];
        expectedResult = service.addContentToCollectionIfMissing(contentCollection, content);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(content);
      });

      it('should add only unique Content to an array', () => {
        const contentArray: IContent[] = [{ id: 123 }, { id: 456 }, { id: 13158 }];
        const contentCollection: IContent[] = [{ id: 123 }];
        expectedResult = service.addContentToCollectionIfMissing(contentCollection, ...contentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const content: IContent = { id: 123 };
        const content2: IContent = { id: 456 };
        expectedResult = service.addContentToCollectionIfMissing([], content, content2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(content);
        expect(expectedResult).toContain(content2);
      });

      it('should accept null and undefined values', () => {
        const content: IContent = { id: 123 };
        expectedResult = service.addContentToCollectionIfMissing([], null, content, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(content);
      });

      it('should return initial array if no Content is added', () => {
        const contentCollection: IContent[] = [{ id: 123 }];
        expectedResult = service.addContentToCollectionIfMissing(contentCollection, undefined, null);
        expect(expectedResult).toEqual(contentCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
