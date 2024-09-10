import { LeftIcon, RightIcon } from "@/icons/ArrowIcons";
import { Button } from "@nextui-org/button"
import { useDate } from "@/hooks/useDate";

export function DateSelector () {

    const {date, setDate} = useDate();
    
    const handlePrevMonth = () => {
        const newDate = new Date(date);
        newDate.setMonth(newDate.getMonth() - 1);
        setDate(newDate);
    }

    const handleNextMonth = () => {
        const newDate = new Date(date);
        newDate.setMonth(newDate.getMonth() + 1);
        setDate(newDate);
    }

    return (
        <div className="flex flex-row items-center">
            <Button 
                className="text-lg text-default-800 cursor-pointer active:opacity-50 rounded-none rounded-s-lg" 
                onClick={handlePrevMonth}
                isIconOnly
            >
                <LeftIcon />
            </Button>
            <Button 
                className="text-lg text-default-800 cursor-pointer active:opacity-50 rounded-none rounded-e-lg" 
                onClick={handleNextMonth}
                isIconOnly
            >
                <RightIcon />
            </Button>
            <h3 className="p-5 text-xl font-semibold">
                {
                    date.toLocaleDateString("en-EN", {year: 'numeric', month: 'long'})
                }
            </h3>
        </div>
    );
}