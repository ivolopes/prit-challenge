export interface User {
    id: number;
    name: string;
    email: string;
    paswword: string;
}

export interface LoginResponse {
    access_token: string;
    data: any;
    name: string;
    status: string;
    message: string;
}