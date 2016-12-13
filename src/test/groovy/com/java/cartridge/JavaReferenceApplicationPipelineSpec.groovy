package com.java.cartridge

import spock.lang.Unroll
import spock.lang.Shared
import spock.lang.Specification

/**
 * Tests that JavaReferenceApplication Pipeline View dsl works as expected.
 */
class JavaReferenceApplicationPipelineSpec extends Specification {

    @Shared
    def helper = new DslHelper('jenkins/jobs/dsl/java_DaaS_jobs.groovy')

    @Shared
    def Node node = new XmlParser().parseText(helper.jm.savedViews["${helper.projectName}/Java_DaaS"])

    def 'view "Environment_Provisioning" exists'() {
        expect:
            helper.jm.savedViews[viewName] != null

        where:
            viewName = "${helper.projectName}/Java_DaaS"
    }

    @Unroll
    def 'title of view is "#pipelineTitle"'() {
        expect:
            node.buildViewTitle.size() == 1
            node.buildViewTitle.text() == pipelineTitle

        where:
            pipelineTitle = 'Reference Application Pipeline'
    }

    @Unroll
    def 'first trigger on job in view is "#jenkinsJobName"'() {
        expect:
            node.selectedJob.size() == 1
            node.selectedJob.text() == jenkinsJobName

        where:
            jenkinsJobName = "${helper.projectName}/DaaS_Build"
    }

    @Unroll
    def 'number of display builds in view is "#numberOfBuilds"'() {
        expect:
            node.noOfDisplayedBuilds.size() == 1
            node.noOfDisplayedBuilds.text() == numberOfBuilds

        where:
            numberOfBuilds = '5'
    }
}
