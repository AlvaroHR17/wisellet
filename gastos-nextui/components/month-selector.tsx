import { useDate } from "@/hooks/useDate";
import { Tabs, Tab } from "@nextui-org/react";
import { useState } from "react";
import {Key} from '@react-types/shared';


export function MonthSelector () {
    const {date, setDate} = useDate();
    const [selectedMonth, setSelectedMonth] = useState(date.toLocaleString('en-EN', {month: 'short'}));

    interface Month {
        name: string;
        key: number;
    }
    
    const monthMap: Month[] = [
        { name: "Jan", key: 0 },
        { name: "Feb", key: 1 },
        { name: "Mar", key: 2 },
        { name: "Apr", key: 3 },
        { name: "May", key: 4 },
        { name: "June", key: 5 },
        { name: "July", key: 6 },
        { name: "Aug", key: 7 },
        { name: "Sep", key: 8 },
        { name: "Oct", key: 9 },
        { name: "Nov", key: 10 },
        { name: "Dec", key: 11 },
    ]
    

    const handleChangeMonth = (key: Key) => {
        const newDate = new Date(date);
        newDate.setMonth(monthMap.find(m => m.name == key.toString())!.key);
        setDate(newDate);
        setSelectedMonth(key.toString());
    }
    
    return (
        <Tabs 
            aria-label="Tabs" 
            className="flex flex-col justify-between"
            selectedKey={selectedMonth}
            onSelectionChange={handleChangeMonth}
        >
            <Tab key="Jan" title="January"/>
            <Tab key="Feb" title="February" />
            <Tab key="Mar" title="March" />
            <Tab key="Apr" title="April" />
            <Tab key="May" title="May" />
            <Tab key="June" title="June"/>
            <Tab key="July" title="July" />
            <Tab key="Aug" title="August" />
            <Tab key="Sep" title="September"/>
            <Tab key="Oct" title="October" />
            <Tab key="Nov" title="November" />
            <Tab key="Dec" title="December" />
        </Tabs>
    )
}