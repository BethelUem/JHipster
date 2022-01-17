import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { UserProgramService } from '../service/user-program.service';
import { IUserProgram, UserProgram } from '../user-program.model';
import { ICatalog } from 'app/entities/catalog/catalog.model';
import { CatalogService } from 'app/entities/catalog/service/catalog.service';

import { UserProgramUpdateComponent } from './user-program-update.component';

describe('UserProgram Management Update Component', () => {
  let comp: UserProgramUpdateComponent;
  let fixture: ComponentFixture<UserProgramUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userProgramService: UserProgramService;
  let catalogService: CatalogService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [UserProgramUpdateComponent],
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
      .overrideTemplate(UserProgramUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserProgramUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userProgramService = TestBed.inject(UserProgramService);
    catalogService = TestBed.inject(CatalogService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Catalog query and add missing value', () => {
      const userProgram: IUserProgram = { id: 456 };
      const catProgram: ICatalog = { id: 93103 };
      userProgram.catProgram = catProgram;

      const catalogCollection: ICatalog[] = [{ id: 70249 }];
      jest.spyOn(catalogService, 'query').mockReturnValue(of(new HttpResponse({ body: catalogCollection })));
      const additionalCatalogs = [catProgram];
      const expectedCollection: ICatalog[] = [...additionalCatalogs, ...catalogCollection];
      jest.spyOn(catalogService, 'addCatalogToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userProgram });
      comp.ngOnInit();

      expect(catalogService.query).toHaveBeenCalled();
      expect(catalogService.addCatalogToCollectionIfMissing).toHaveBeenCalledWith(catalogCollection, ...additionalCatalogs);
      expect(comp.catalogsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const userProgram: IUserProgram = { id: 456 };
      const catProgram: ICatalog = { id: 54425 };
      userProgram.catProgram = catProgram;

      activatedRoute.data = of({ userProgram });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(userProgram));
      expect(comp.catalogsSharedCollection).toContain(catProgram);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserProgram>>();
      const userProgram = { id: 123 };
      jest.spyOn(userProgramService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userProgram });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userProgram }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(userProgramService.update).toHaveBeenCalledWith(userProgram);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserProgram>>();
      const userProgram = new UserProgram();
      jest.spyOn(userProgramService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userProgram });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userProgram }));
      saveSubject.complete();

      // THEN
      expect(userProgramService.create).toHaveBeenCalledWith(userProgram);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserProgram>>();
      const userProgram = { id: 123 };
      jest.spyOn(userProgramService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userProgram });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userProgramService.update).toHaveBeenCalledWith(userProgram);
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
