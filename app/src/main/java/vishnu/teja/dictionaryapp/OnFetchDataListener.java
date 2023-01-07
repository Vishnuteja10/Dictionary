package vishnu.teja.dictionaryapp;

import vishnu.teja.dictionaryapp.models.APIResponse;

public interface OnFetchDataListener {

    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
