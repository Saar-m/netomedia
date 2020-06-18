## NetoMedia Project Description
In this project I was tasked with using Google Cloud Platform, Kubernetes, Jenkins, Github and Docker to build CI/CD for a web app.  
A quick rundown on my take on it all:
Application: According to the specification I packaged a main page "Hello World" on https://nm.saar.io and a more demanding application on https://nm.saar.io/intensive . [Source](https://github.com/Hextris/hextris) into an Nginx image. I wrote a Helm chart to package and install it. Read it at Clusterconfig/product.
I've used Helm all throughout the project to create a more portable and cloud agnostic solution.
[Jenkins](https://jenkins.saar.io) is native in the cluster and uses dynamics slave provisioning,reads and self installs its pipeline libraries containing JobDSL along with Groovy and Bash to build, deploy and test the app.Read more about it in ClusterConfig/jenkins .
Ingress is load balancing and TLS management is done using Cert-Manager.
To allow for the application's Horizontal Pod Autoscaling metrics-server was installed.
I intentionally built a fully featured production grade cluster to support the application when it scales and as the company adds more of them. Less complex solutions might have done the trick over the short term but If and when I join NetoMedia I'd rather build all my infrastructure as code once and augment it as time allows. 
This project is only ~85% complete as of 18.6.20 - a few reconfigurations needed but all in all I hope I demonstraed my fluency in all the technologies and their integration into a complete solution in a quick and clear way.
Even though the project had simpler requests in mind I hope this was satisfactory. I'll keep building and improving it if you'll extend the deadline over the weekend, after which I'll migrate it away to my private cluster to save your expenses.
Please feel free to [email](mailto:hire.saar@gmail.com) me further questions about the architecture choices and solution, I'll be happy to elaborate!