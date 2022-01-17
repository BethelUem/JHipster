import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CatalogDetailComponent } from './catalog-detail.component';

describe('Catalog Management Detail Component', () => {
  let comp: CatalogDetailComponent;
  let fixture: ComponentFixture<CatalogDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CatalogDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ catalog: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CatalogDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CatalogDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load catalog on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.catalog).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
