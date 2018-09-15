package com.whackedblocks.plugin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Exchange extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50611069806100206000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806324e21cde146100b457806327457b3b146101275780633543b23c1461019357806342966c68146101ee5780634806a0bd1461021b5780635714cc8e146102485780635d5b9021146102e057806366c89a131461030d5780637d1108f51461037a5780638373ae71146104205780638d5802e214610437575b600080fd5b3480156100c057600080fd5b50610125600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019082018035906020019190919293919293908035906020019082018035906020019190919293919293905050506104a4565b005b34801561013357600080fd5b5061013c6104ab565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561017f578082015181840152602081019050610164565b505050509050019250505060405180910390f35b34801561019f57600080fd5b506101ec600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390505050610543565b005b3480156101fa57600080fd5b5061021960048036038101908080359060200190929190505050610710565b005b34801561022757600080fd5b5061024660048036038101908080359060200190929190505050610a4e565b005b34801561025457600080fd5b50610289600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a51565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156102cc5780820151818401526020810190506102b1565b505050509050019250505060405180910390f35b3480156102ec57600080fd5b5061030b60048036038101908080359060200190929190505050610aeb565b005b34801561031957600080fd5b5061033860048036038101908080359060200190929190505050610aee565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561038657600080fd5b506103a560048036038101908080359060200190929190505050610b38565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156103e55780820151818401526020810190506103ca565b50505050905090810190601f1680156104125780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561042c57600080fd5b50610435610bfa565b005b34801561044357600080fd5b5061046260048036038101908080359060200190929190505050610bfc565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b5050505050565b6060600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000180548060200260200160405190810160405280929190818152602001828054801561053957602002820191906000526020600020905b815481526020019060010190808311610525575b5050505050905090565b60016060604051908101604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff16815260200184848080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050508152509080600181540180825580915050906001820390600052602060002090600302016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190610694929190610f24565b50505050600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160018080549050039080600181540180825580915050906001820390600052602060002001600090919290919091505550505050565b60003373ffffffffffffffffffffffffffffffffffffffff1660018381548110151561073857fe5b906000526020600020906003020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610841576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260418152602001807f496e206f7264657220746f206275726e20616e2061737365742c20796f75206e81526020017f65656420746f20626520746865206f6e652077686f20656d697474656420697481526020017f2e0000000000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b600090505b6002600060018481548110151561085957fe5b906000526020600020906003020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805490508110156109cc5781600260006001858154811015156108e757fe5b906000526020600020906003020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018281548110151561096357fe5b906000526020600020015414156109bf576109be60018381548110151561098657fe5b906000526020600020906003020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683610c46565b5b8080600101915050610846565b6001828154811015156109db57fe5b9060005260206000209060030201600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600282016000610a489190610fa4565b50505050565b50565b6060600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805480602002602001604051908101604052809291908181526020018280548015610adf57602002820191906000526020600020905b815481526020019060010190808311610acb575b50505050509050919050565b50565b6000600182815481101515610aff57fe5b906000526020600020906003020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6060600182815481101515610b4957fe5b90600052602060002090600302016002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bee5780601f10610bc357610100808354040283529160200191610bee565b820191906000526020600020905b815481529060010190602001808311610bd157829003601f168201915b50505050509050919050565b565b6000600182815481101515610c0d57fe5b906000526020600020906003020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600080600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805490509150600090505b600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000180549050811015610d595782600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000182815481101515610d3357fe5b90600052602060002001541415610d4c57829150610d59565b8080600101915050610c96565b600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018054905082101515610dac57610f1e565b8190505b6001600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018054905003811015610ec757600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160018201815481101515610e5257fe5b9060005260206000200154600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000182815481101515610eac57fe5b90600052602060002001819055508080600101915050610db0565b600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805480919060019003610f1c9190610fec565b505b50505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f6557805160ff1916838001178555610f93565b82800160010185558215610f93579182015b82811115610f92578251825591602001919060010190610f77565b5b509050610fa09190611018565b5090565b50805460018160011615610100020316600290046000825580601f10610fca5750610fe9565b601f016020900490600052602060002090810190610fe89190611018565b5b50565b815481835581811115611013578183600052602060002091820191016110129190611018565b5b505050565b61103a91905b8082111561103657600081600090555060010161101e565b5090565b905600a165627a7a72305820b74679daee82c4cb0185cf8511204698b0043e254c8bd7a46b6517d01c9724510029";

    public static final String FUNC_ACCEPTTRADEOFFER = "acceptTradeOffer";

    public static final String FUNC_ASSIGN = "assign";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_CANCELTRADEOFFER = "cancelTradeOffer";

    public static final String FUNC_DECLINETRADEOFFER = "declineTradeOffer";

    public static final String FUNC_SENDTRADEOFFER = "sendTradeOffer";

    public static final String FUNC_GETASSETDATA = "getAssetData";

    public static final String FUNC_GETASSETEMMITER = "getAssetEmmiter";

    public static final String FUNC_GETMYINVENTORY = "getMyInventory";

    public static final String FUNC_GETUSERINVENTORY = "getUserInventory";

    public static final Event MODIFYTRADEOFFER_EVENT = new Event("ModifyTradeOffer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event ASSETMOVE_EVENT = new Event("AssetMove", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ASSETBURN_EVENT = new Event("AssetBurn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event NEWTRADEOFFER_EVENT = new Event("NewTradeOffer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event ASSETASSIGN_EVENT = new Event("AssetAssign", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected Exchange(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Exchange(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<ModifyTradeOfferEventResponse> getModifyTradeOfferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MODIFYTRADEOFFER_EVENT, transactionReceipt);
        ArrayList<ModifyTradeOfferEventResponse> responses = new ArrayList<ModifyTradeOfferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ModifyTradeOfferEventResponse typedResponse = new ModifyTradeOfferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.state = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ModifyTradeOfferEventResponse> modifyTradeOfferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ModifyTradeOfferEventResponse>() {
            @Override
            public ModifyTradeOfferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MODIFYTRADEOFFER_EVENT, log);
                ModifyTradeOfferEventResponse typedResponse = new ModifyTradeOfferEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.state = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ModifyTradeOfferEventResponse> modifyTradeOfferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MODIFYTRADEOFFER_EVENT));
        return modifyTradeOfferEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> acceptTradeOffer(BigInteger _offer_id) {
        final Function function = new Function(
                FUNC_ACCEPTTRADEOFFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_offer_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> assign(String _owner, String _data) {
        final Function function = new Function(
                FUNC_ASSIGN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner), 
                new org.web3j.abi.datatypes.Utf8String(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> burn(BigInteger _id) {
        final Function function = new Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cancelTradeOffer() {
        final Function function = new Function(
                FUNC_CANCELTRADEOFFER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> declineTradeOffer(BigInteger _offer_id) {
        final Function function = new Function(
                FUNC_DECLINETRADEOFFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_offer_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sendTradeOffer(String _partner, List<BigInteger> _my_items, List<BigInteger> _their_items) {
        final Function function = new Function(
                FUNC_SENDTRADEOFFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_partner), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_my_items, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_their_items, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<AssetMoveEventResponse> getAssetMoveEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ASSETMOVE_EVENT, transactionReceipt);
        ArrayList<AssetMoveEventResponse> responses = new ArrayList<AssetMoveEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AssetMoveEventResponse typedResponse = new AssetMoveEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AssetMoveEventResponse> assetMoveEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, AssetMoveEventResponse>() {
            @Override
            public AssetMoveEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ASSETMOVE_EVENT, log);
                AssetMoveEventResponse typedResponse = new AssetMoveEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<AssetMoveEventResponse> assetMoveEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ASSETMOVE_EVENT));
        return assetMoveEventObservable(filter);
    }

    public List<AssetBurnEventResponse> getAssetBurnEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ASSETBURN_EVENT, transactionReceipt);
        ArrayList<AssetBurnEventResponse> responses = new ArrayList<AssetBurnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AssetBurnEventResponse typedResponse = new AssetBurnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AssetBurnEventResponse> assetBurnEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, AssetBurnEventResponse>() {
            @Override
            public AssetBurnEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ASSETBURN_EVENT, log);
                AssetBurnEventResponse typedResponse = new AssetBurnEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<AssetBurnEventResponse> assetBurnEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ASSETBURN_EVENT));
        return assetBurnEventObservable(filter);
    }

    public List<NewTradeOfferEventResponse> getNewTradeOfferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWTRADEOFFER_EVENT, transactionReceipt);
        ArrayList<NewTradeOfferEventResponse> responses = new ArrayList<NewTradeOfferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewTradeOfferEventResponse typedResponse = new NewTradeOfferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.my_items = (List<BigInteger>) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.their_items = (List<BigInteger>) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewTradeOfferEventResponse> newTradeOfferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewTradeOfferEventResponse>() {
            @Override
            public NewTradeOfferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWTRADEOFFER_EVENT, log);
                NewTradeOfferEventResponse typedResponse = new NewTradeOfferEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.my_items = (List<BigInteger>) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.their_items = (List<BigInteger>) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewTradeOfferEventResponse> newTradeOfferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWTRADEOFFER_EVENT));
        return newTradeOfferEventObservable(filter);
    }

    public List<AssetAssignEventResponse> getAssetAssignEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ASSETASSIGN_EVENT, transactionReceipt);
        ArrayList<AssetAssignEventResponse> responses = new ArrayList<AssetAssignEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AssetAssignEventResponse typedResponse = new AssetAssignEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.user = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.emitter = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.data = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AssetAssignEventResponse> assetAssignEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, AssetAssignEventResponse>() {
            @Override
            public AssetAssignEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ASSETASSIGN_EVENT, log);
                AssetAssignEventResponse typedResponse = new AssetAssignEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.user = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.emitter = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.data = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<AssetAssignEventResponse> assetAssignEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ASSETASSIGN_EVENT));
        return assetAssignEventObservable(filter);
    }

    public RemoteCall<String> getAssetData(BigInteger _id) {
        final Function function = new Function(FUNC_GETASSETDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getAssetEmmiter(BigInteger _id) {
        final Function function = new Function(FUNC_GETASSETEMMITER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getMyInventory() {
        final Function function = new Function(FUNC_GETMYINVENTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getUserInventory(String _address) {
        final Function function = new Function(FUNC_GETUSERINVENTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public static RemoteCall<Exchange> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Exchange.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Exchange> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Exchange.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Exchange load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Exchange(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Exchange load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Exchange(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class ModifyTradeOfferEventResponse {
        public Log log;

        public BigInteger id;

        public BigInteger state;
    }

    public static class AssetMoveEventResponse {
        public Log log;

        public BigInteger id;

        public String from;

        public String to;
    }

    public static class AssetBurnEventResponse {
        public Log log;

        public BigInteger id;
    }

    public static class NewTradeOfferEventResponse {
        public Log log;

        public BigInteger id;

        public String sender;

        public String receiver;

        public List<BigInteger> my_items;

        public List<BigInteger> their_items;
    }

    public static class AssetAssignEventResponse {
        public Log log;

        public BigInteger id;

        public String user;

        public String emitter;

        public String data;
    }
}
