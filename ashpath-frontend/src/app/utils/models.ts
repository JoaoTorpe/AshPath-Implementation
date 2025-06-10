export interface LoginRequest {
    email: string;
    password: string;
}

export interface SuccessfulLoginResponse {
    loggedUserId: number;
    appRoleSet: AppRole[];
}

export interface AbstractCreateUserRequest {
    userId: number;
    fullname: string;
    email: string;
    password: string;
    repeatPassword: string;
}

export interface CreateAdminRequest extends AbstractCreateUserRequest {
}

export interface CreateViewerRequest extends AbstractCreateUserRequest {
}

export interface CreateNecrotomistRequest extends AbstractCreateUserRequest {
    specialization: string;
}

export interface AbstractUserResponse {
    id: number;
    email: string;
    fullname: string;
    registrationDate: string;
    lastActivityDate: string;
}

export interface AdminUserResponse extends AbstractUserResponse {
}

export interface ViewerUserResponse extends AbstractUserResponse {
}

export interface NecrotomistUserResponse extends AbstractUserResponse {
    specialization: string;
}

export enum AppRole {
    ADMIN = "ADMIN",
    VIEWER = "VIEWER",
    NECROTOMIST = "NECROTOMIST",
}