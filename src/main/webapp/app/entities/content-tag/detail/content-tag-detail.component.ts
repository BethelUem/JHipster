import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContentTag } from '../content-tag.model';

@Component({
  selector: 'jhi-content-tag-detail',
  templateUrl: './content-tag-detail.component.html',
})
export class ContentTagDetailComponent implements OnInit {
  contentTag: IContentTag | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contentTag }) => {
      this.contentTag = contentTag;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
