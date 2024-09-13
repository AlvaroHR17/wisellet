import { LeftIcon, RightIcon } from "@/icons/ArrowIcons";
import { Button } from "@nextui-org/button"
import { useDate } from "@/hooks/useDate";

export function YearSelector () {

    const {date, setDate} = useDate();
    
    const handlePrevYear = () => {
        const newDate = new Date(date);
        newDate.setFullYear(newDate.getFullYear() - 1);
        setDate(newDate);
    }

    const handleNextYear = () => {
        const newDate = new Date(date);
        newDate.setFullYear(newDate.getFullYear() + 1);
        setDate(newDate);
    }

    return (
        <div className="flex flex-row items-center">
            <Button 
                className="text-lg text-default-800 cursor-pointer active:opacity-50 rounded-none rounded-s-lg" 
                onClick={handlePrevYear}
                isIconOnly
            >
                <LeftIcon />
            </Button>
            <Button 
                className="text-lg text-default-800 cursor-pointer active:opacity-50 rounded-none rounded-e-lg" 
                onClick={handleNextYear}
                isIconOnly
            >
                <RightIcon />
            </Button>
            <h3 className="p-5 text-xl font-semibold">
                {
                    date.toLocaleDateString("en-EN", {year: 'numeric'})
                }
            </h3>
        </div>
    );
}