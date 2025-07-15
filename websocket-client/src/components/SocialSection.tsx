import type { User } from "../models/User"
import UserCard from "./UserCard"

interface SocialSectionProps {
    readonly users: User[]
}

export default function SocialSection({ users }: SocialSectionProps) {
    return (
        <div className="grid-container">
            {users.map(user => (
                <div className="grid-item" key={user.id}>
                    <UserCard name={user.fullName} url={user.imageUrl || ""} message={user.messages?.[user.messages.length - 1] || ""} />
                </div>
            ))}
        </div>
    )
}