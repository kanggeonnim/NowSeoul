const CLIENT_ID = "97f3803597017d8afc5cd9b9a5696cfa";
const REDIRECT_URI = "http://localhost:5173/kakao/callback";

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;