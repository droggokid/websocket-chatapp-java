interface ButtonProps {
    readonly label: string;
    readonly onClick: () => void;
    readonly disabled?: boolean;
    readonly variant?: 'primary' | 'success' | 'danger' | 'secondary';
    readonly size?: 'small' | 'medium' | 'large';
}

export default function Button({
    label,
    onClick,
    disabled = false,
    variant = 'primary',
    size = 'medium'
}: ButtonProps) {

    const defaultStyle = () => {
        return {
            backgroundColor: '#007bff',
            color: '#fff',
            borderColor: '#007bff'
        };
    }
    const getVariantStyles = () => {
        if (disabled) {
            return {
                backgroundColor: '#e9ecef',
                color: '#6c757d',
                borderColor: '#dee2e6'
            };
        }

        switch (variant) {
            case 'primary':
                return {
                    backgroundColor: '#007bff',
                    color: '#fff',
                    borderColor: '#007bff'
                };
            case 'success':
                return {
                    backgroundColor: '#28a745',
                    color: '#fff',
                    borderColor: '#28a745'
                };
            case 'danger':
                return {
                    backgroundColor: '#dc3545',
                    color: '#fff',
                    borderColor: '#dc3545'
                };
            case 'secondary':
                return {
                    backgroundColor: '#6c757d',
                    color: '#fff',
                    borderColor: '#6c757d'
                };
            default:
                return defaultStyle();
        }
    };

    const getSizeStyles = () => {
        switch (size) {
            case 'small':
                return {
                    padding: '6px 12px',
                    fontSize: '12px',
                    fontWeight: '500'
                };
            case 'medium':
                return {
                    padding: '8px 16px',
                    fontSize: '14px',
                    fontWeight: '500'
                };
            case 'large':
                return {
                    padding: '12px 24px',
                    fontSize: '16px',
                    fontWeight: '600'
                };
            default:
                return defaultStyle();
        }
    };

    const baseStyles = {
        border: '1px solid',
        borderRadius: '6px',
        cursor: disabled ? 'not-allowed' : 'pointer',
        fontFamily: 'inherit',
        lineHeight: '1.5',
        textAlign: 'center' as const,
        textDecoration: 'none',
        verticalAlign: 'middle',
        userSelect: 'none' as const,
        transition: 'all 0.2s ease-in-out',
        outline: 'none',
        display: 'inline-block',
        margin: '0 8px 8px 0',
        boxShadow: disabled ? 'none' : '0 2px 4px rgba(0, 0, 0, 0.1)',
        opacity: disabled ? 0.65 : 1,
        transform: 'translateY(0)',
        ...getSizeStyles(),
        ...getVariantStyles()
    };

    const handleMouseEnter = (e: React.MouseEvent<HTMLButtonElement>) => {
        if (!disabled) {
            e.currentTarget.style.transform = 'translateY(-1px)';
            e.currentTarget.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.15)';

            const currentBg = getVariantStyles().backgroundColor;
            if (currentBg === '#007bff') e.currentTarget.style.backgroundColor = '#0056b3';
            else if (currentBg === '#28a745') e.currentTarget.style.backgroundColor = '#1e7e34';
            else if (currentBg === '#dc3545') e.currentTarget.style.backgroundColor = '#c82333';
            else if (currentBg === '#6c757d') e.currentTarget.style.backgroundColor = '#545b62';
        }
    };

    const handleMouseLeave = (e: React.MouseEvent<HTMLButtonElement>) => {
        if (!disabled) {
            e.currentTarget.style.transform = 'translateY(0)';
            e.currentTarget.style.boxShadow = '0 2px 4px rgba(0, 0, 0, 0.1)';
            e.currentTarget.style.backgroundColor = getVariantStyles().backgroundColor;
        }
    };

    const handleMouseDown = (e: React.MouseEvent<HTMLButtonElement>) => {
        if (!disabled) {
            e.currentTarget.style.transform = 'translateY(1px)';
            e.currentTarget.style.boxShadow = '0 1px 2px rgba(0, 0, 0, 0.1)';
        }
    };

    const handleMouseUp = (e: React.MouseEvent<HTMLButtonElement>) => {
        if (!disabled) {
            e.currentTarget.style.transform = 'translateY(0)';
            e.currentTarget.style.boxShadow = '0 2px 4px rgba(0, 0, 0, 0.1)';
        }
    };

    return (
        <button
            type="button"
            onClick={onClick}
            disabled={disabled}
            style={baseStyles}
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
            onMouseDown={handleMouseDown}
            onMouseUp={handleMouseUp}
        >
            {label}
        </button>
    );
}