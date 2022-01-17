import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ContentDetailComponent } from './content-detail.component';

describe('Content Management Detail Component', () => {
  let comp: ContentDetailComponent;
  let fixture: ComponentFixture<ContentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ content: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ContentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ContentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load content on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.content).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
