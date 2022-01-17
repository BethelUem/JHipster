import dayjs from 'dayjs/esm';
import { ICatalog } from 'app/entities/catalog/catalog.model';

export interface IUserProgram {
  id?: number;
  user?: number | null;
  active?: boolean | null;
  createById?: number | null;
  createdDate?: dayjs.Dayjs | null;
  lastModifiedById?: number | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  catProgram?: ICatalog | null;
}

export class UserProgram implements IUserProgram {
  constructor(
    public id?: number,
    public user?: number | null,
    public active?: boolean | null,
    public createById?: number | null,
    public createdDate?: dayjs.Dayjs | null,
    public lastModifiedById?: number | null,
    public lastModifiedDate?: dayjs.Dayjs | null,
    public catProgram?: ICatalog | null
  ) {
    this.active = this.active ?? false;
  }
}

export function getUserProgramIdentifier(userProgram: IUserProgram): number | undefined {
  return userProgram.id;
}
