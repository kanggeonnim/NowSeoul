<script setup></script>

<template>
  <div class="frame">
    <h1
      class="title"
      style="font-style: italic font-weight: bold;
  text-shadow: 2px 2px 6px gray;"
    >
      회원가입
    </h1>
    <p class="pname">아이디</p>

    <input type="text" id="id" />

    <p class="pname">비밀번호</p>

    <input type="password" id="pw" />

    <p class="pname">이름</p>

    <input type="text" id="name" />

    <p class="pname">이메일</p>

    <input type="email" id="email" />

    <p class="pname">전화번호</p>

    <input type="text" id="pNo" />
  </div>
</template>

<style scoped>
.frame {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  letter-spacing: 10px;
  line-height: 10px;
  word-spacing: 10px;
}
.pname {
  font-weight: bold;
  font-size: 15px;
  text-shadow: 2px 2px 6px gray;
  font-style: italic;
}
.title {
  height: 10vh;
}
</style>
