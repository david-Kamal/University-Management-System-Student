# University Management System – Student App

A native Android application that provides university students with a digital platform to manage their academic life. The app lets students register, view courses, exam schedules, attendance records, results, and communicate with peers through group chat.

## Features

- **User Authentication:** Register and log in using secure credentials (Firebase Auth).
- **Subject Information:** 
  - View registered subjects
  - See remaining and attended subjects
- **Exam Schedule:** 
  - View upcoming and past exams
- **Attendance Tracking:** 
  - Check attendance record for each subject
- **Results:** 
  - View grades/results for completed subjects
- **Chat & Communication:** 
  - Real-time chat with friends and groups using Firebase
- **Session Management:** 
  - Persistent login and session storage

## Tech Stack

- **Android/Java:** Main application logic and UI
- **Firebase:** Authentication and chat backend
- **Retrofit:** API communication

## Getting Started

1. Clone the repo:
   ```
   git clone https://github.com/david-Kamal/University-Management-System-Student.git
   ```
2. Open in Android Studio.
3. Configure Firebase as described in the project wiki or documentation.
4. Build and run on an emulator or device.

## Folder Structure

- `app/src/main/java/com/reham/`
  - `modules/` – Data models and network modules
  - `chat/` – Chat activities and logic
  - `modernacademystudent/` – Main UI activities

## Feedback

- Code is modular and leverages modern Android practices.
- Firebase integration is effective for chat and authentication.
- Consider adding more UI/UX polish and comprehensive error handling.
- Adding in-app documentation or user onboarding would further improve user experience.

---

## License

[MIT](LICENSE)
