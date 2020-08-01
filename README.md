# spring-boot-with-soap
Spring Boot SOAP with Restful Web Services using Jersey:

Requriment :
------------

<img src="https://github.com/90rajibgarai/doc-important-links/blob/master/Architectures/Spring Boot Soap with Rest/Requirement/Screenshot_1.jpg">

1) SOAP Web Service - Create a Web Service as it's own web application/project. The service must connect to a database and allow users to perform CRUD operations on the data using the SOAP web service. This application should not contain a web interface, only a SOAP service that you will connect to.
2) Admin Application - Create a web application that will allow clients to perform CRUD operations on the data by calling the SOAP web service. This project will contain a web interface, and a Web Service Client (to the Web Service Project). The web project will likely need to use server side based calls to the SOAP service, so you will likely use JSP or Servlets to create the admin application.
3) Frontend Content Application - Create a web application that will allow users to view content. The application should show a listing of available shows and movies, allowing a user to select one to watch. Once selected, the user should see the show details (running time, title, producer, director, etc...) and a thumbnail preview. This application will retrieve content to display from the REST service (not the SOAP service and not directly from the database). The application should make it's API REST calls from the front end (via JavaScript or a JavaScript framework). This application can be built with anything you wish (JSP, Spring)
4) REST Service - The rest service will need to connect to the SOAP service in order to retrieve content. The REST service will only read content and not update, delete or create content. Do not connect the REST service directly to the database. When a request comes from the front end application (get me the list of movies, for example) the REST service will call the SOAP service, get results, and return the results back to the front end application in JSON format. The REST service must use JAX-RS, not Spring REST.

Follow Requried Steps :
-----------------------
soap-web-service : [Spring Boot with SOAP API Integeration]

1) Create a spring boot project with following library : [Spring web service, devtools, lombok, jpa, mysql]

2) Add another depedency : wsdl4j

3) create a xsd file in resource directory for the entity

4) Add a maven plugin 'xjc' for auto generated class from xsd file.

5) Now run comment maven => 'mvn clean install' using JDK

6) After successfully compile All requried clases are create in xsd specific package.

7) Now we write down our endpoint business logic.

8) Now run your application.

9) Application running port 9090.

10) Now test your SOAP application using your favorite postman or SOUP UI.

11) Get Generated XSD Link from here : http://localhost:9090/ws/program.wsdl

Postman Testing Guidline : 
--------------------------

1) HTTP Requested method always "POST"

2) Requested URI always "http://localhost:9090/ws/program.wsdl"

3) Set Content-Type : text/xml in header section

4) Get WSDL methods from using "Wizdler" Google Chorme plugin/apps :Parses the WSDL files and generates SOAP messages for you.

5) Following requested are used for various action :

A) SAVE-PROGRAM:

    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <saveProgramRequest xmlns="http://www.soap.com/ws/config/xsd">
              <title>Peaky Blinders</title>
              <type>TV Show</type>
              <producer>Caryn Mandabach, Greg Brenman, Steven Knight</producer>
              <director>Cillian Murphy, Jamie Glazebrook, Frith Tiplady</director>
              <content>6865726F6E6779616E672E636F6D</content>
              <runningTime>2020-10-19T09:30:10Z</runningTime>
              <addedBy>3</addedBy>
              <updatedBy>3</updatedBy>
          </saveProgramRequest>
      </Body>
    </Envelope>

B) GET-ALL-PROGRAMS:

    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <getAllProgramsRequest xmlns="http://www.soap.com/ws/config/xsd">[any]</getAllProgramsRequest>
      </Body>
    </Envelope>

C) GET-PROGRAM-INFO:

    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <getProgramRequest xmlns="http://www.soap.com/ws/config/xsd">
              <id>4</id>
          </getProgramRequest>
      </Body>
    </Envelope>

D) UPDATE-PROGRAM:

    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <updateProgramRequest xmlns="http://www.soap.com/ws/config/xsd">
              <id>4</id>
              <title>Arrow</title>
              <type>TV Show</type>
              <producer>Greg Berlanti</producer>
              <director>Marc Guggenheim, Andrew Kreisberg</director>
              <content>3F3C6D78206C657673726F693D6E3122302E20226E656F636964676E223D54552D4622383E3F</content>
              <runningTime>2020-09-19T09:30:10</runningTime>
              <addedBy>5</addedBy>
              <updatedBy>5</updatedBy>
          </updateProgramRequest>
      </Body>
    </Envelope>

E) DELETE-PROGRAM:

    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
      <Body>
          <deleteProgramRequest xmlns="http://www.soap.com/ws/config/xsd">
              <id>5</id>
          </deleteProgramRequest>
      </Body>
    </Envelope>


Client Service Integration :
-----------------------------

Here I provide two Restful web service(Using JAX-RS Jersey) :

A) admin-rest-service
B) client-rest-service

A) admin-rest-service :
You can do all CRUD operations using admin-rest-service. It's internally call your "soap-web-service" through SOAP Client. All database operation written in your soap-web-service.

B) client-rest-service : 
Here you can do only Get Operation using rest call. It's also internally call your "soap-web-service" through SOAP Client. The all Database operation All database operation written in your soap-web-service. 

Process : 
---------

1) Create a web base project with fillowing library : [Spring web service, devtools, lombok, jersey, spring-oxm, spring-ws-core]
2) Now add folder name wsdl in resource and add a file program.wsdl. Then copy pest wsdl content from http://localhost:9090/ws/program.wsdl
3) Now add a plugin in pom.xml : maven-jaxb2-plugin => to convert JSON to XML conversion for SOAP Client call
4) Now  'mvn clean install' using JDK
5) Then Develop Rest Service using Jersey [not Spring Rest]
6) Then Run soap-web-service, admin-rest-service, client-rest-service.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Generated Sample WSDL file : http://localhost:9090/ws/program.wsdl

    <wsdl:definitions
      xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
      xmlns:sch="http://www.soap.com/ws/config/xsd"
      xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
      xmlns:tns="http://www.soap.com/ws/config/xsd"
      targetNamespace="http://www.soap.com/ws/config/xsd">
      <wsdl:types>
        <xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema"
          elementFormDefault="qualified"
          targetNamespace="http://www.soap.com/ws/config/xsd">
          <!-- SAVE PROGRAM -->
          <xsd:element name="saveProgramRequest">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="title" type="xsd:string" />
                <xsd:element name="type" type="xsd:string" />
                <xsd:element name="producer" type="xsd:string" />
                <xsd:element name="director" type="xsd:string" />
                <xsd:element name="content" type="xsd:hexBinary" />
                <xsd:element name="runningTime" type="xsd:dateTime" />
                <xsd:element name="addedBy" type="xsd:int" />
                <xsd:element name="updatedBy" type="xsd:int" />
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <!-- GET ALL PROGRAMS -->
          <xsd:element name="getAllProgramsRequest">
            <xsd:complexType />
          </xsd:element>
          <xsd:element name="getAllProgramsResponse">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element maxOccurs="unbounded" minOccurs="0"
                  name="programs" type="tns:program" />
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <!-- GET PROGRAM BY ID -->
          <xsd:element name="getProgramRequest">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="id" type="xsd:int" />
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <xsd:element name="getProgramResponse">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="program" type="tns:program" />
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <!-- UPDATE PROGRAM -->
          <xsd:element name="updateProgramRequest">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="id" type="xsd:int" />
                <xsd:element name="title" type="xsd:string" />
                <xsd:element name="type" type="xsd:string" />
                <xsd:element name="producer" type="xsd:string" />
                <xsd:element name="director" type="xsd:string" />
                <xsd:element name="content" type="xsd:hexBinary" />
                <xsd:element name="runningTime" type="xsd:dateTime" />
                <xsd:element name="addedBy" type="xsd:int" />
                <xsd:element name="updatedBy" type="xsd:int" />
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <!-- DELETE PROGRAM -->
          <xsd:element name="deleteProgramRequest">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="id" type="xsd:int" />
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <!-- GET MESSAGE -->
          <xsd:element name="getMessageResponse">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="messgae" type="xsd:string" />
              </xsd:sequence>
            </xsd:complexType>
          </xsd:element>
          <!-- PROGRAM MODEL -->
          <xsd:complexType name="program">
            <xsd:sequence>
              <xsd:element name="id" type="xsd:int" />
              <xsd:element name="title" type="xsd:string" />
              <xsd:element name="type" type="xsd:string" />
              <xsd:element name="producer" type="xsd:string" />
              <xsd:element name="director" type="xsd:string" />
              <xsd:element name="content" type="xsd:hexBinary" />
              <xsd:element name="runningTime" type="xsd:dateTime" />
              <xsd:element name="addedBy" type="xsd:int" />
              <xsd:element name="updatedBy" type="xsd:int" />
            </xsd:sequence>
          </xsd:complexType>
          <!-- END -->
        </xsd:schema>
      </wsdl:types>
      <wsdl:message name="getProgramRequest">
        <wsdl:part element="tns:getProgramRequest"
          name="getProgramRequest">
        </wsdl:part>
      </wsdl:message>
      <wsdl:message name="getMessageResponse">
        <wsdl:part element="tns:getMessageResponse"
          name="getMessageResponse">
        </wsdl:part>
      </wsdl:message>
      <wsdl:message name="deleteProgramRequest">
        <wsdl:part element="tns:deleteProgramRequest"
          name="deleteProgramRequest">
        </wsdl:part>
      </wsdl:message>
      <wsdl:message name="getAllProgramsResponse">
        <wsdl:part element="tns:getAllProgramsResponse"
          name="getAllProgramsResponse">
        </wsdl:part>
      </wsdl:message>
      <wsdl:message name="getProgramResponse">
        <wsdl:part element="tns:getProgramResponse"
          name="getProgramResponse">
        </wsdl:part>
      </wsdl:message>
      <wsdl:message name="updateProgramRequest">
        <wsdl:part element="tns:updateProgramRequest"
          name="updateProgramRequest">
        </wsdl:part>
      </wsdl:message>
      <wsdl:message name="saveProgramRequest">
        <wsdl:part element="tns:saveProgramRequest"
          name="saveProgramRequest">
        </wsdl:part>
      </wsdl:message>
      <wsdl:message name="getAllProgramsRequest">
        <wsdl:part element="tns:getAllProgramsRequest"
          name="getAllProgramsRequest">
        </wsdl:part>
      </wsdl:message>
      <wsdl:portType name="ProgramServicePort">
        <wsdl:operation name="getProgram">
          <wsdl:input message="tns:getProgramRequest"
            name="getProgramRequest">
          </wsdl:input>
          <wsdl:output message="tns:getProgramResponse"
            name="getProgramResponse">
          </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="getMessage">
          <wsdl:output message="tns:getMessageResponse"
            name="getMessageResponse">
          </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="deleteProgram">
          <wsdl:input message="tns:deleteProgramRequest"
            name="deleteProgramRequest">
          </wsdl:input>
        </wsdl:operation>
        <wsdl:operation name="getAllPrograms">
          <wsdl:input message="tns:getAllProgramsRequest"
            name="getAllProgramsRequest">
          </wsdl:input>
          <wsdl:output message="tns:getAllProgramsResponse"
            name="getAllProgramsResponse">
          </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="updateProgram">
          <wsdl:input message="tns:updateProgramRequest"
            name="updateProgramRequest">
          </wsdl:input>
        </wsdl:operation>
        <wsdl:operation name="saveProgram">
          <wsdl:input message="tns:saveProgramRequest"
            name="saveProgramRequest">
          </wsdl:input>
        </wsdl:operation>
      </wsdl:portType>
      <wsdl:binding name="ProgramServicePortSoap11"
        type="tns:ProgramServicePort">
        <soap:binding style="document"
          transport="http://schemas.xmlsoap.org/soap/http" />
        <wsdl:operation name="getProgram">
          <soap:operation soapAction="" />
          <wsdl:input name="getProgramRequest">
            <soap:body use="literal" />
          </wsdl:input>
          <wsdl:output name="getProgramResponse">
            <soap:body use="literal" />
          </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="getMessage">
          <soap:operation soapAction="" />
          <wsdl:output name="getMessageResponse">
            <soap:body use="literal" />
          </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="deleteProgram">
          <soap:operation soapAction="" />
          <wsdl:input name="deleteProgramRequest">
            <soap:body use="literal" />
          </wsdl:input>
        </wsdl:operation>
        <wsdl:operation name="getAllPrograms">
          <soap:operation soapAction="" />
          <wsdl:input name="getAllProgramsRequest">
            <soap:body use="literal" />
          </wsdl:input>
          <wsdl:output name="getAllProgramsResponse">
            <soap:body use="literal" />
          </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="updateProgram">
          <soap:operation soapAction="" />
          <wsdl:input name="updateProgramRequest">
            <soap:body use="literal" />
          </wsdl:input>
        </wsdl:operation>
        <wsdl:operation name="saveProgram">
          <soap:operation soapAction="" />
          <wsdl:input name="saveProgramRequest">
            <soap:body use="literal" />
          </wsdl:input>
        </wsdl:operation>
      </wsdl:binding>
      <wsdl:service name="ProgramServicePortService">
        <wsdl:port binding="tns:ProgramServicePortSoap11"
          name="ProgramServicePortSoap11">
          <soap:address location="http://localhost:9090/ws" />
        </wsdl:port>
      </wsdl:service>
    </wsdl:definitions>
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
Testing Screen Shots: https://github.com/90rajibgarai/doc-important-links/blob/master/Architectures/Spring Boot Soap with Rest/Screen Shots/

SOAP API CALL :

<img src="https://github.com/90rajibgarai/doc-important-links/blob/master/Architectures/Spring Boot Soap with Rest/Screen Shots/SOAP-API-SAVE_PROGRAM.jpg">

ADMIN REST API CALL : 

<img src="https://github.com/90rajibgarai/doc-important-links/blob/master/Architectures/Spring Boot Soap with Rest/Screen Shots/REST-ADMIN-SAVE-PROGRAM.jpg">

CLIENT REST API CALL :

<img src="https://github.com/90rajibgarai/doc-important-links/blob/master/Architectures/Spring Boot Soap with Rest/Screen Shots/REST-CLIENT-GET-ALL-PROGRAMS.jpg">



