"use client";

import * as React from "react";
import { NextUIProvider } from "@nextui-org/system";
import { useRouter } from "next/navigation";
import { ThemeProvider as NextThemesProvider } from "next-themes";
import { ThemeProviderProps } from "next-themes/dist/types";
import { createContext, useState } from "react";

interface DateContextType {
  date: Date;
  setDate: (newDate: Date) => void;
}
export const DateContext = createContext<DateContextType | undefined>(undefined);

interface LoadingContextType {
  loading: boolean;
  showLoading: () => void;
  hideLoading: () => void;
}
const LoadingContext = createContext<LoadingContextType | undefined>(undefined);

export const useLoading = (): LoadingContextType => {
  const context = React.useContext(LoadingContext);
  if (!context) {
    throw new Error('useLoading must be used within a LoadingProvider');
  }
  return context;
};
export interface ProvidersProps {
  children: React.ReactNode;
  themeProps?: ThemeProviderProps;
}

export function Providers({ children, themeProps }: ProvidersProps) {
  const router = useRouter();
  const [date, setDate] = useState(new Date());
  const [loading, setLoading] = useState(false);

  const showLoading = () => setLoading(true);
  const hideLoading = () => setLoading(false);
  
  return (
    <NextUIProvider navigate={router.push}>
      <NextThemesProvider {...themeProps}>
        <DateContext.Provider value={{date, setDate}}>
          <LoadingContext.Provider value={{loading, showLoading, hideLoading}}>
            {children}
          </LoadingContext.Provider>
        </DateContext.Provider>
      </NextThemesProvider>
    </NextUIProvider>
  );
}
