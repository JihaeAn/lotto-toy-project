# 🎰 로또 번호 자동 생성 사이트

로또 번호를 자동 생성해주고, 스케줄링을 통해 매주 토요일 오후 9시 20분에 API와 크롤링을 통해 당첨 번호와 통계 자료들을 받아와 구현한 개인 프로젝트입니다.

## 📆 개발 기간
2024년 10월 28일(월) - 11월 23일(토)
- 데이터베이스 설계
- AWS 서버 구축
- 기능 개발
- 리눅스를 통한 애플리케이션 배포

## 👀 프로젝트 소개
- 로또 생성 버튼을 통해 동적으로 번호 생성 후 생성 내역 DB에 저장
- 스케줄링을 통한 매주 토요일 오후 9시 20분마다 자동으로 설정 기능 실행
  - REST API 통신하여 금주 당첨번호 데이터 DB에 저장
  - 금주 당첨 통계 내역 DB에 저장
  - 금주 1등 복권 판매점 정보 DB에 저장 + API를 통한 카카오 맵 지도 동적 조회
- 금주 당첨 번호 데이터와 사이트 내 사용자가 생성한 번호 데이터를 대조하여 당첨 내역 동적으로 화면에 노출
- 회차 번호 입력하여 해당 회차 당첨 번호 조회
- 최근 6개월 간 가장 많이 나온 번호 통계 데이터 동적으로 화면에 노출
- 당첨금 실수령액 계산

## 💻 개발 환경
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <br>
  <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
  <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"> 
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white">
</div>
