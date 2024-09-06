import { useRef } from 'react';
import useScript from '../hooks/useScript';

export default function GoogleLogin({
  onGoogleSignIn = () => {}
}) {
  const googleSignInButton = useRef(null);
  const text = 'signin_with';

  useScript('https://accounts.google.com/gsi/client', () => {
    window.google.accounts.id.initialize({
      client_id: '473002568705-hn26t4s024fkotvi6c3kbcnm0u73ql3b.apps.googleusercontent.com',
      callback: onGoogleSignIn,
    });
    window.google.accounts.id.renderButton(
      googleSignInButton.current!,
      { theme: 'filled_blue', size: 'large', text, width: 250, type: 'standard' },
    );
  });

  return <div ref={googleSignInButton}></div>;
}