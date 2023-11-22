import axios from "axios";

const kakaoAuthHandle = async () => {
  const code = new URL(window.location.href).searchParams.get("code");
  if (code) {
    try {
      const response = await axios.get(`http://localhost:3000/kakao/callback?code=${code}`);
      localStorage.setItem("token", response.headers.authorization);
      console.log("response is " + response);
      window.location.href = "/";
    } catch (error) {
      console.error("Error handling Kakao authentication:", error);
      // Handle error as needed
    }
  }
};

export { kakaoAuthHandle };
