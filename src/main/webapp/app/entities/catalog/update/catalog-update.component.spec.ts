import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CatalogService } from '../service/catalog.service';
import { ICatalog, Catalog } from '../catalog.model';

import { CatalogUpdateComponent } from './catalog-update.component';

describe('Catalog Management Update Component', () => {
  let comp: CatalogUpdateComponent;
  let fixture: ComponentFixture<CatalogUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let catalogService: CatalogService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CatalogUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(CatalogUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CatalogUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    catalogService = TestBed.inject(CatalogService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Catalog query and add missing value', () => {
      const catalog: ICatalog = { id: 456 };
      const parentId: ICatalog = { id: 72672 };
      catalog.parentId = parentId;

      const catalogCollection: ICatalog[] = [{ id: 73587 }];
      jest.spyOn(catalogService, 'query').mockReturnValue(of(new HttpResponse({ body: catalogCollection })));
      const additionalCatalogs = [parentId];
      const expectedCollection: ICatalog[] = [...additionalCatalogs, ...catalogCollection];
      jest.spyOn(catalogService, 'addCatalogToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ catalog });
      comp.ngOnInit();

      expect(catalogService.query).toHaveBeenCalled();
      expect(catalogService.addCatalogToCollectionIfMissing).toHaveBeenCalledWith(catalogCollection, ...additionalCatalogs);
      expect(comp.catalogsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const catalog: ICatalog = { id: 456 };
      const parentId: ICatalog = { id: 44804 };
      catalog.parentId = parentId;

      activatedRoute.data = of({ catalog });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(catalog));
      expect(comp.catalogsSharedCollection).toContain(parentId);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Catalog>>();
      const catalog = { id: 123 };
      jest.spyOn(catalogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: catalog }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(catalogService.update).toHaveBeenCalledWith(catalog);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Catalog>>();
      const catalog = new Catalog();
      jest.spyOn(catalogService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: catalog }));
      saveSubject.complete();

      // THEN
      expect(catalogService.create).toHaveBeenCalledWith(catalog);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Catalog>>();
      const catalog = { id: 123 };
      jest.spyOn(catalogService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalog });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(catalogService.update).toHaveBeenCalledWith(catalog);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackCatalogById', () => {
      it('Should return tracked Catalog primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackCatalogById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
