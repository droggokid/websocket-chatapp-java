export interface User {
    id: number;
    fullName: string;
    imageUrl?: string;
    email?: string;
    username?: string;
    lastSeen?: Date;
    createdAt?: Date;
    messages?: string[];
}

export interface UserStatus {
    userId: number;
    isOnline: boolean;
    lastActivity: Date;
    socketId?: string;
}

export interface Message {
    id: string;
    content: string;
    timestamp: Date;
    senderId: string;
    senderName: string;
    type?: 'text' | 'image' | 'file';
}