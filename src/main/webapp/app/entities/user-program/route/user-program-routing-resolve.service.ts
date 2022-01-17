import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUserProgram, UserProgram } from '../user-program.model';
import { UserProgramService } from '../service/user-program.service';

@Injectable({ providedIn: 'root' })
export class UserProgramRoutingResolveService implements Resolve<IUserProgram> {
  constructor(protected service: UserProgramService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserProgram> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((userProgram: HttpResponse<UserProgram>) => {
          if (userProgram.body) {
            return of(userProgram.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserProgram());
  }
}
