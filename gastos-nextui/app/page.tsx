'use client'
import React, { useEffect, useState } from "react";

import "@/styles/globals.css";
import clsx from "clsx";

import { Providers } from "./providers";
import { fontSans } from "@/config/fonts";
import { Navbar } from "@/components/navbar";
import { Home } from "@/components/home";
import { getAuth, invalidateToken } from "@/axios/AxiosLogin";
import { postLoginToken } from '@/axios/AxiosLogin';
import { CredentialResponse, GoogleLogin, GoogleOAuthProvider } from "@react-oauth/google";

export default function App() {

  const [isLoggedIn, setLoggedIn] = useState(false);

  const onGoogleSignIn = async (res:CredentialResponse) => {
    const { credential } = res;
    if(credential){
      const result = await postLoginToken(credential);
      setLoggedIn(result);
    }
  };

  const logout = () => {
    invalidateToken();
    setLoggedIn(false);
  }

  useEffect(() => {
    const getAuthorization = async () => {
      setLoggedIn(await getAuth());
    }
    getAuthorization();
  }, []);

  return (
    <html suppressHydrationWarning lang="en">
      <head />
      <body
        className={clsx(
          "min-h-screen bg-background font-sans antialiased",
          fontSans.variable,
        )}
      >
        <Providers themeProps={{ attribute: "class", defaultTheme: "dark" }}>
          {isLoggedIn ? 
            <div className="relative flex flex-col h-screen">
              <Navbar logout={logout} />
              <main className="container mx-auto max-w-7xl pt-16 px-6 flex-grow">
                <Home/>
              </main>
              <footer className="w-full flex items-center justify-center py-3">
                {/* <Link
                  isExternal
                  className="flex items-center gap-1 text-current"
                  href="https://nextui-docs-v2.vercel.app?utm_source=next-app-template"
                  title="nextui.org homepage"
                >
                  <span className="text-default-600">Powered by</span>
                  <p className="text-primary">NextUI</p>
                </Link> */}
              </footer>
            </div>
          :
          
          <GoogleOAuthProvider clientId="473002568705-hn26t4s024fkotvi6c3kbcnm0u73ql3b.apps.googleusercontent.com">
            <GoogleLogin 
              onSuccess={onGoogleSignIn} 
              onError={() => console.error("ERROR")}></GoogleLogin>
          </GoogleOAuthProvider>
          }
        </Providers>
      </body>
    </html>
  )
}