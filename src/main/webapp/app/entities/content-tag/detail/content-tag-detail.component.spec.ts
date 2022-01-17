import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ContentTagDetailComponent } from './content-tag-detail.component';

describe('ContentTag Management Detail Component', () => {
  let comp: ContentTagDetailComponent;
  let fixture: ComponentFixture<ContentTagDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContentTagDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ contentTag: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ContentTagDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ContentTagDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load contentTag on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.contentTag).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
