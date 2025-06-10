export interface LoginRequest {
  email: string;
  password: string;
}

export interface SuccessfulLoginResponse {
  loggedUserId: number;
  appRoleSet: AppRole[];
}

export interface CreateAdminRequest {
  fullname: string;
  email: string;
  password: string;
  repeatPassword: string;
}

export interface CreateNecrotomistRequest {
  fullname: string;
  specialization: string;
  email: string;
  password: string;
  repeatPassword: string;
}

export interface AdminUserResponse {
  id: number;
  email: string;
  fullname: string;
  registrationDate: string;
  lastActivityDate: string;
}

export interface NecrotomistUserResponse {
  id: number;
  email: string;
  fullname: string;
  registrationDate: string;
  lastActivityDate: string;
  specialization: string;
}

export enum AppRole {
  ADMIN = 'ADMIN',
  NECROTOMIST = 'NECROTOMIST',
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
}

export interface CremationEntryResponse {
  id: number;
  creationDate: string;
  necrotomist: NecrotomistUserResponse;
  deceaseds: DeceasedDetailResponse[];
}

export enum DeceasedStatus {
  AWAITING_CREMATION = 'AWAITING_CREMATION',
  CREMATED = 'CREMATED',
  IN_GRAVE = 'IN_GRAVE',
}
