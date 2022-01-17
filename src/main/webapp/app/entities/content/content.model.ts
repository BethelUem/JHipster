import dayjs from 'dayjs/esm';
import { IContentTag } from 'app/entities/content-tag/content-tag.model';
import { ICatalog } from 'app/entities/catalog/catalog.model';

export interface IContent {
  id?: number;
  recordingDate?: dayjs.Dayjs | null;
  editionDate?: dayjs.Dayjs | null;
  signatureOriginal?: string | null;
  signatureEdited?: string | null;
  eventReason?: string | null;
  eventSlogan?: string | null;
  nameConduction?: string | null;
  positionConduction?: string | null;
  specialtiesPanel?: string | null;
  namePanel?: string | null;
  titleSubject?: string | null;
  biblicalPassage?: string | null;
  synopsisDescription?: string | null;
  storyline?: string | null;
  mediaContent?: string | null;
  durationOriginal?: string | null;
  durationEdited?: string | null;
  scenography?: string | null;
  location?: string | null;
  city?: string | null;
  departmentState?: string | null;
  producer?: string | null;
  productionAssistant?: string | null;
  operatorVTRPlayOut?: string | null;
  productionCredits?: string | null;
  castActors?: string | null;
  archiveStatus?: string | null;
  interpreterTranslator?: string | null;
  dubbing?: string | null;
  subtitleCC?: string | null;
  cataloguerOriginal?: string | null;
  cataloguerEdited?: string | null;
  postproductionEditor?: string | null;
  operatorIngestion?: string | null;
  serviceOTT?: boolean | null;
  archivadoLTOOriginal?: boolean | null;
  archivadoLTOEdited?: boolean | null;
  observations?: string | null;
  rightsManagement?: string | null;
  cataloguerProduction?: string | null;
  cataloguerIngest?: string | null;
  cataloguerMaster?: string | null;
  urlEdition?: string | null;
  urlOriginal?: string | null;
  createById?: number | null;
  createdDate?: dayjs.Dayjs | null;
  lastModifiedById?: number | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  catTags?: IContentTag[] | null;
  catContentType?: ICatalog | null;
  catTypeConduction?: ICatalog | null;
  catProgram?: ICatalog | null;
  catShiftRecording?: ICatalog | null;
  catTargetAudience?: ICatalog | null;
  catCountry?: ICatalog | null;
  catProductionCompany?: ICatalog | null;
  catArchivalCollection?: ICatalog | null;
  catOriginalLanguage?: ICatalog | null;
  catInterpreterLanguage?: ICatalog | null;
  catDubbingLanguage?: ICatalog | null;
  catSubtitleLanguage?: ICatalog | null;
  catTvCensorship?: ICatalog | null;
  catClassificationCategory?: ICatalog | null;
  catGenreProgram?: ICatalog | null;
  catFormatProgram?: ICatalog | null;
  catResolutionOriginal?: ICatalog | null;
  catResolutionEdited?: ICatalog | null;
  catCatalogingLevelOriginal?: ICatalog | null;
  catCatalogingLevelEdited?: ICatalog | null;
  catVideoQuality?: ICatalog | null;
  catAudioQuality?: ICatalog | null;
  catMusicalGroup?: ICatalog | null;
  catMusicalGenre?: ICatalog | null;
  catApproved?: ICatalog | null;
}

export class Content implements IContent {
  constructor(
    public id?: number,
    public recordingDate?: dayjs.Dayjs | null,
    public editionDate?: dayjs.Dayjs | null,
    public signatureOriginal?: string | null,
    public signatureEdited?: string | null,
    public eventReason?: string | null,
    public eventSlogan?: string | null,
    public nameConduction?: string | null,
    public positionConduction?: string | null,
    public specialtiesPanel?: string | null,
    public namePanel?: string | null,
    public titleSubject?: string | null,
    public biblicalPassage?: string | null,
    public synopsisDescription?: string | null,
    public storyline?: string | null,
    public mediaContent?: string | null,
    public durationOriginal?: string | null,
    public durationEdited?: string | null,
    public scenography?: string | null,
    public location?: string | null,
    public city?: string | null,
    public departmentState?: string | null,
    public producer?: string | null,
    public productionAssistant?: string | null,
    public operatorVTRPlayOut?: string | null,
    public productionCredits?: string | null,
    public castActors?: string | null,
    public archiveStatus?: string | null,
    public interpreterTranslator?: string | null,
    public dubbing?: string | null,
    public subtitleCC?: string | null,
    public cataloguerOriginal?: string | null,
    public cataloguerEdited?: string | null,
    public postproductionEditor?: string | null,
    public operatorIngestion?: string | null,
    public serviceOTT?: boolean | null,
    public archivadoLTOOriginal?: boolean | null,
    public archivadoLTOEdited?: boolean | null,
    public observations?: string | null,
    public rightsManagement?: string | null,
    public cataloguerProduction?: string | null,
    public cataloguerIngest?: string | null,
    public cataloguerMaster?: string | null,
    public urlEdition?: string | null,
    public urlOriginal?: string | null,
    public createById?: number | null,
    public createdDate?: dayjs.Dayjs | null,
    public lastModifiedById?: number | null,
    public lastModifiedDate?: dayjs.Dayjs | null,
    public catTags?: IContentTag[] | null,
    public catContentType?: ICatalog | null,
    public catTypeConduction?: ICatalog | null,
    public catProgram?: ICatalog | null,
    public catShiftRecording?: ICatalog | null,
    public catTargetAudience?: ICatalog | null,
    public catCountry?: ICatalog | null,
    public catProductionCompany?: ICatalog | null,
    public catArchivalCollection?: ICatalog | null,
    public catOriginalLanguage?: ICatalog | null,
    public catInterpreterLanguage?: ICatalog | null,
    public catDubbingLanguage?: ICatalog | null,
    public catSubtitleLanguage?: ICatalog | null,
    public catTvCensorship?: ICatalog | null,
    public catClassificationCategory?: ICatalog | null,
    public catGenreProgram?: ICatalog | null,
    public catFormatProgram?: ICatalog | null,
    public catResolutionOriginal?: ICatalog | null,
    public catResolutionEdited?: ICatalog | null,
    public catCatalogingLevelOriginal?: ICatalog | null,
    public catCatalogingLevelEdited?: ICatalog | null,
    public catVideoQuality?: ICatalog | null,
    public catAudioQuality?: ICatalog | null,
    public catMusicalGroup?: ICatalog | null,
    public catMusicalGenre?: ICatalog | null,
    public catApproved?: ICatalog | null
  ) {
    this.serviceOTT = this.serviceOTT ?? false;
    this.archivadoLTOOriginal = this.archivadoLTOOriginal ?? false;
    this.archivadoLTOEdited = this.archivadoLTOEdited ?? false;
  }
}

export function getContentIdentifier(content: IContent): number | undefined {
  return content.id;
}
