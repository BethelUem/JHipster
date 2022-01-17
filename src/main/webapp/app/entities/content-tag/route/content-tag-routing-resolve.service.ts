import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IContentTag, ContentTag } from '../content-tag.model';
import { ContentTagService } from '../service/content-tag.service';

@Injectable({ providedIn: 'root' })
export class ContentTagRoutingResolveService implements Resolve<IContentTag> {
  constructor(protected service: ContentTagService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContentTag> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((contentTag: HttpResponse<ContentTag>) => {
          if (contentTag.body) {
            return of(contentTag.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ContentTag());
  }
}
