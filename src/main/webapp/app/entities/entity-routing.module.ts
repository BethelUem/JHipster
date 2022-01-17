import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'content',
        data: { pageTitle: 'proyectoBethelApp.content.home.title' },
        loadChildren: () => import('./content/content.module').then(m => m.ContentModule),
      },
      {
        path: 'content-tag',
        data: { pageTitle: 'proyectoBethelApp.contentTag.home.title' },
        loadChildren: () => import('./content-tag/content-tag.module').then(m => m.ContentTagModule),
      },
      {
        path: 'user-program',
        data: { pageTitle: 'proyectoBethelApp.userProgram.home.title' },
        loadChildren: () => import('./user-program/user-program.module').then(m => m.UserProgramModule),
      },
      {
        path: 'catalog',
        data: { pageTitle: 'proyectoBethelApp.catalog.home.title' },
        loadChildren: () => import('./catalog/catalog.module').then(m => m.CatalogModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
