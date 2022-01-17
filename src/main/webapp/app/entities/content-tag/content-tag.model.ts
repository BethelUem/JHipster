import dayjs from 'dayjs/esm';
import { IContent } from 'app/entities/content/content.model';
import { ICatalog } from 'app/entities/catalog/catalog.model';

export interface IContentTag {
  id?: number;
  createById?: number | null;
  createdDate?: dayjs.Dayjs | null;
  lastModifiedById?: number | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  contentId?: IContent;
  catalogId?: ICatalog;
}

export class ContentTag implements IContentTag {
  constructor(
    public id?: number,
    public createById?: number | null,
    public createdDate?: dayjs.Dayjs | null,
    public lastModifiedById?: number | null,
    public lastModifiedDate?: dayjs.Dayjs | null,
    public contentId?: IContent,
    public catalogId?: ICatalog
  ) {}
}

export function getContentTagIdentifier(contentTag: IContentTag): number | undefined {
  return contentTag.id;
}
