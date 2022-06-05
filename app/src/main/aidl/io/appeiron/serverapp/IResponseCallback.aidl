// IResponseCallback.aidl
package io.appeiron.serverapp;

// Interface for the response callback

interface IResponseCallback {
    void onSuccess(String requestId,String msg);
    void onFailure(String msg);
}