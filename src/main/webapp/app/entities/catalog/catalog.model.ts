import dayjs from 'dayjs/esm';
import { IContentTag } from 'app/entities/content-tag/content-tag.model';

export interface ICatalog {
  id?: number;
  code?: string | null;
  name?: string | null;
  description?: string | null;
  active?: boolean | null;
  createById?: number | null;
  createdDate?: dayjs.Dayjs | null;
  lastModifiedById?: number | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  contentTags?: IContentTag[] | null;
  parentId?: ICatalog | null;
}

export class Catalog implements ICatalog {
  constructor(
    public id?: number,
    public code?: string | null,
    public name?: string | null,
    public description?: string | null,
    public active?: boolean | null,
    public createById?: number | null,
    public createdDate?: dayjs.Dayjs | null,
    public lastModifiedById?: number | null,
    public lastModifiedDate?: dayjs.Dayjs | null,
    public contentTags?: IContentTag[] | null,
    public parentId?: ICatalog | null
  ) {
    this.active = this.active ?? false;
  }
}

export function getCatalogIdentifier(catalog: ICatalog): number | undefined {
  return catalog.id;
}
