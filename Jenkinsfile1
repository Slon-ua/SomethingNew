node {
//
    environment {
//        APP_DOCKER_SANDBOX_VERSION = sh (
//            script: 'awk -F "." \'{print $1"."$2"."$3"."$4+1}\' /var/lib/jenkins/conf/APP_DOCKER_SANDBOX_VERSION',
//            returnStdout: true
//            ).trim()
//       WISE_DOCKER_SANDBOX_VERSION = sh (
//            script: 'awk -F "." \'{print $1"."$2"."$3"."$4+1}\' /var/lib/jenkins/conf/WISE_DOCKER_SANDBOX_VERSION',
//            returnStdout: true
//            ).trim()
//        FLEET_DOCKER_SANDBOX_VERSION = sh (
//            script: 'awk -F "." \'{print $1"."$2"."$3"."$4+1}\' /var/lib/jenkins/conf/FLEET_DOCKER_SANDBOX_VERSION',
//            returnStdout: true
//            ).trim()
//        FLEET_DOCKER_PROD_VERSION = sh (
//            script: 'awk -F "." \'{print $1"."$2"."$3"."$4+1}\' /var/lib/jenkins/conf/FLEET_DOCKER_PROD_VERSION',
//            returnStdout: true
//            ).trim()

        CHATID="-382252210"
        TOKEN="342643054:AAEANh8JIpn1Oq4csQi9-HHnSUmy5HYFjU8"
    //curl -s -X POST https://api.telegram.org/bot342643054:AAEANh8JIpn1Oq4csQi9-HHnSUmy5HYFjU8/sendMessage -d chat_id=-382252210 -d text='Build Sandbox Fleet started.'
    }    

    stage("checkout repo"){
//        steps {
                sh "curl -s -X POST https://api.telegram.org/bot342643054:AAEANh8JIpn1Oq4csQi9-HHnSUmy5HYFjU8/sendMessage -d chat_id=221338397 -d text='Start AUTest.'"
//            }
        git branch: 'master',
        credentials: '',
        url: 'https://github.com/Slon-ua/SomethingNew.git'
    }
    stage("build"){
//        steps {
                sh "curl -s -X POST https://api.telegram.org/bot342643054:AAEANh8JIpn1Oq4csQi9-HHnSUmy5HYFjU8/sendMessage -d chat_id=221338397 -d text='Build AUProject.'"
//            }
        sh "./gradlew clean API:assemble "
    }
    try {
        stage("run API test"){
//          steps {
              sh "curl -s -X POST https://api.telegram.org/bot342643054:AAEANh8JIpn1Oq4csQi9-HHnSUmy5HYFjU8/sendMessage -d chat_id=221338397 -d text='Run API test.'"
//          }
            sh "./gradlew  API:test" 
        }
        stage("run UI test"){
//          steps {
             sh "curl -s -X POST https://api.telegram.org/bot342643054:AAEANh8JIpn1Oq4csQi9-HHnSUmy5HYFjU8/sendMessage -d chat_id=221338397 -d text='Run UI test.'"
//          }
            sh "./gradlew  UI:test"        }
    } catch (error) {
        currentBuild.result = 'FAILURE'
        throw error
    } finally {
        stage('Reports') {
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'API/build/allure-results'],[path: 'UI/build/allure-results']]
            ])
        }
//        steps {
                sh "curl -s -X POST https://api.telegram.org/bot342643054:AAEANh8JIpn1Oq4csQi9-HHnSUmy5HYFjU8/sendMessage -d chat_id=221338397 -d text='Test complete.\n\n\n .'"
//        }
    }
}
