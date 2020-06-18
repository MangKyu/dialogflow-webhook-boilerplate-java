gc
Boilerplate to help you get started quickly with the Java client library for Actions on Google.

### Setup Instructions

#### Action Configuration
1. From the [Actions on Google Console](https://console.actions.google.com/), New project (this will become your *Project ID*) > **Create Project**.
1. Scroll down to the **More Options** section, and click on the **Conversational** card.
1. From the top menu under **Develop** > **Actions** (left nav) > **Add your first action** > **BUILD** (this will bring you to the Dialogflow console) > Select language and time zone > **CREATE**.
1. In Dialogflow, go to **Settings** ⚙ > **Export and Import** > **Restore from zip**.
    + Follow the directions to restore from the `agent.zip` file in this repo.

#### App Engine Deployment & Webhook Configuration
When a new project is created using the Actions Console, it also creates a Google Cloud project in the background.
1. Delete ActionsAWSHandler.java
1. Remove the following line from build.gradle:
     + `apply from: 'build-aws.gradle'`
1. Download & install the [Google Cloud SDK](https://cloud.google.com/sdk/docs/)
1. Configure the gcloud CLI and set your Google Cloud project to the name of your Actions on Google Project ID, which you can find from the [Actions on Google console](https://console.actions.google.com/) under Settings ⚙
    + `gcloud init`
    + `gcloud auth application-default login`
    + `gcloud components install app-engine-java`
    + `gcloud components update`
1. Deploy to [App Engine using Gradle](https://cloud.google.com/appengine/docs/flexible/java/using-gradle):
    + `gradle appengineDeploy` OR
    +  From within IntelliJ, open the Gradle tray and run the appEngineDeploy task.
1. Back in the [Dialogflow console](https://console.dialogflow.com), from the left navigation menu under **Fulfillment** > **Enable Webhook**, set the value of **URL** to `https://<YOUR_PROJECT_ID>.appspot.com` > **Save**.

#### Build for AWS
1. Delete ActionsServlet
1. Remove the following line from build.gradle:
    + `apply from: 'build-gcp.gradle'`
1. Build the AWS Lambda compatible zip file using the buildAWSZip gradle task: `gradle buildAWSZip`
1. Deploy the zip file found at `build/distributions/myactions.zip` as an AWS Lambda function by following instructions at https://aws.amazon.com/lambda/

#### Testing this Sample
+ In the [Dialogflow console](https://console.dialogflow.com), from the left navigation menu > **Integrations** > **Integration Settings** under Google Assistant > Enable **Auto-preview changes** >  **Test** to open the Actions on Google simulator. **OR**
+ Type `Talk to my test app` in the simulator, or say `OK Google, talk to my test app` to Google Assistant on a mobile device associated with your Action's account.

### References & Issues
+ Questions? Go to [StackOverflow](https://stackoverflow.com/questions/tagged/actions-on-google), [Assistant Developer Community on Reddit](https://www.reddit.com/r/GoogleAssistantDev/) or [Support](https://developers.google.com/assistant/support).
+ For bugs, please report an issue on Github.
+ Actions on Google [Documentation](https://developers.google.com/assistant)
+ [Webhook Boilerplate Template](https://github.com/actions-on-google/dialogflow-webhook-boilerplate-java) for Actions on Google.
+ More info about [Gradle & the App Engine Plugin](https://cloud.google.com/appengine/docs/flexible/java/using-gradle).
+ More info about deploying [Java apps with App Engine](https://cloud.google.com/appengine/docs/standard/java/quickstart).
 
### Make Contributions
Please read and follow the steps in the [CONTRIBUTING.md](CONTRIBUTING.md).
 
### License
See [LICENSE](LICENSE).
 
### Terms
Your use of this sample is subject to, and by using or downloading the sample files you agree to comply with, the [Google APIs Terms of Service](https://developers.google.com/terms/).



# 사용법
1. 웹훅 서버를 생성하고, 웹훅 코드를 작성하여 서버를 실행시킴

2. API 발급 URL로 접속하여 키를 발급받음
https://accounts.google.com/o/oauth2/v2/auth?
scope=https://www.googleapis.com/auth/calendar.events.readonly&
access_type=offline&
include_granted_scopes=true&state=state_parameter_passthrough_value&
redirect_uri=http://localhost:8091/rd&response_type=code&client_id=248471656671-r8lkjne2krdjeg7o1kgjn1prk9b1gu9c.apps.googleusercontent.com

3. RetrofitClient의 token을 발급받은 토큰에 맞게 수정함
4. 구글 AppEngine을 deploy 시킴
5. 어시스턴트를 적용시킴 

# 구글 결제 계좌 설정
https://console.developers.google.com/billing/projects

# CURL
curl -H "Authorization: Bearer ya29.a0AfH6SMAm1A4bOYRkntyI6A9JwzJRb_zCqOlphDscdq89M2KLr8TL8BY2QvNloI2M43GN1aXDGCtjlGp6kAKhH-qUF9Fx0h3leoE6QDZVFPAU3RwijwaLtpIt0iX7EaLLDRcYAyP-H7yIXDSuyVZSj7zR-ybRRCrcuSk" https://www.googleapis.com/calendar/v3/calendars/whalsrb1226@gmail.com/events?singleEvents=true&timeMax=2020-05-05T00:00:00Z&timeMin=2020-05-05T23:00:00Z