package com.aws.ec2.transitgatewayvpcattachment;

import com.aws.ec2.transitgatewayvpcattachment.workflow.create.Create;
import com.aws.ec2.transitgatewayvpcattachment.workflow.read.Read;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.cloudformation.exceptions.CfnInvalidRequestException;
import software.amazon.cloudformation.proxy.*;

public class CreateHandler extends BaseHandlerStd {

    protected ProgressEvent<com.aws.ec2.transitgatewayvpcattachment.ResourceModel, CallbackContext> handleRequest(
        final AmazonWebServicesClientProxy proxy,
        final ResourceHandlerRequest<ResourceModel> request,
        final CallbackContext callbackContext,
        final ProxyClient<Ec2Client> proxyClient,
        final Logger logger) {

        System.out.println("lfjalfjal");
        if(callbackContext.getAttempts() == 1 &&  (request.getDesiredResourceState().getId() != null)) throw new CfnInvalidRequestException("Attempting to set a ReadOnly Property.");
        return ProgressEvent.progress(request.getDesiredResourceState(), callbackContext)
            .then(new Create(proxy, request, callbackContext, proxyClient, logger)::run)
            .then(new Read(proxy, request, callbackContext, proxyClient, logger)::run);
    }
}
