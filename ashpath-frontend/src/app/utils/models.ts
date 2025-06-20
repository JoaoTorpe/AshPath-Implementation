export interface LoginRequest {
  email: string;
  password: string;
}

export interface SuccessfulLoginResponse {
  loggedUserId: number;
  appRoleSet: AppRoleResponse[];
}

export interface AppRoleResponse {
  id: number;
  name: AppRole;
}

export interface AbstractCreateUserRequest {
  fullname: string;
  email: string;
  password: string;
  repeatPassword: string;
}

export interface CreateAdminRequest extends AbstractCreateUserRequest {}

export interface CreateViewerRequest extends AbstractCreateUserRequest {}

export interface CreateNecrotomistRequest extends AbstractCreateUserRequest {
  specialization: string;
}

export interface AbstractUserResponse {
  id: number;
  email: string;
  fullname: string;
  registrationDate: string;
  lastActivityDate: string;
  appRoleSet: AppRoleResponse[];
}

export interface AdminUserResponse extends AbstractUserResponse {}

export interface ViewerUserResponse extends AbstractUserResponse {}

export interface NecrotomistUserResponse extends AbstractUserResponse {
  specialization: string;
}

export type UserResponse = AdminUserResponse & ViewerUserResponse & NecrotomistUserResponse;

export enum AppRole {
  VIEWER = 'VIEWER',
  NECROTOMIST = 'NECROTOMIST',
  ADMIN = 'ADMIN',
}

export interface UserCredentials {
  id: number;
}

export interface DeceasedDetailResponse {
  id: number;
  fullname: string;
  birthDate: string;
  deathDate: string;
  causeOfDeath: string;
  fatherName: string;
  motherName: string;
  status: DeceasedStatus;
  cremationEnteredDate: string;
  graveLocation?: string;
}

export interface DeceasedResponse {
  id: number;
  fullname: string;
  birthDate: string; // ISO date string (LocalDate no backend)
  deathDate: string; // ISO date string (LocalDate no backend)
  causeOfDeath: string;
  deathCertificateDownloadLink?: string;
  fatherName: string;
  motherName: string;
  status: DeceasedStatus;
  cremationEnteredDate?: string; // ISO datetime string (LocalDateTime no backend)
  cremationEntryId?: number;
  graveLocation?: string;
}

export interface CremationEntryResponse {
  id: number;
  creationDate: string;
  necrotomist: NecrotomistUserResponse;
  deceaseds?: DeceasedDetailResponse[];
}

export enum DeceasedStatus {
  WAITING_CREMATION = 'WAITING_CREMATION',
  CREMATED = 'CREMATED',
  GRAVED = 'GRAVED',
}
