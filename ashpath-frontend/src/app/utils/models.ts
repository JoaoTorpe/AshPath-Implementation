export interface LoginRequest {
    email: string;
    password: string;
}

export interface SuccessfulLoginResponse {
    loggedUserId: number;
    appRole: AppRole[];
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
    ADMIN = "ADMIN",
    NECROTOMIST = "NECROTOMIST",
}