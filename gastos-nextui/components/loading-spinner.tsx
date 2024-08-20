import { Spinner } from "@nextui-org/react";

export interface LoadingSpinnerProps {
    isVisible: Boolean;
}

export const LoadingSpinner = () => {
    return(
        <div className="fixed h-screen w-screen top-0 left-0 z-50 flex justify-center items-center">
            <div className="fixed h-screen w-screen top-0 bg-gray-600 opacity-35"></div>
            <Spinner size="lg" className="opacity-100"/>
        </div>
    );
}