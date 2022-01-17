import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UserProgramDetailComponent } from './user-program-detail.component';

describe('UserProgram Management Detail Component', () => {
  let comp: UserProgramDetailComponent;
  let fixture: ComponentFixture<UserProgramDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserProgramDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ userProgram: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(UserProgramDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UserProgramDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load userProgram on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.userProgram).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
