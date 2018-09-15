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
    private static final String BINARY = "60806040523480156200001157600080fd5b506060806001608060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020016020604051908101604052806000815250815260200160206040519081016040528060008152508152509080600181540180825580915050906001820390600052602060002090600402016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020190805190602001906200015e92919062000312565b5060608201518160030190805190602001906200017d92919062000312565b50505050600060a060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff16815260200184815260200183815260200160016003811115620001e357fe5b8152509080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190620002bd92919062000399565b506060820151816003019080519060200190620002dc92919062000399565b5060808201518160040160006101000a81548160ff021916908360038111156200030257fe5b0217905550505050505062000413565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200035557805160ff191683800117855562000386565b8280016001018555821562000386579182015b828111156200038557825182559160200191906001019062000368565b5b509050620003959190620003eb565b5090565b828054828255906000526020600020908101928215620003d8579160200282015b82811115620003d7578251825591602001919060010190620003ba565b5b509050620003e79190620003eb565b5090565b6200041091905b808211156200040c576000816000905550600101620003f2565b5090565b90565b61290280620004236000396000f3006080604052600436106100ba576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631ac204a8146100bf57806324e21cde1461010457806327457b3b1461018b5780633543b23c146101f757806342966c68146102525780634806a0bd1461027f5780635714cc8e146102ac5780635d5b90211461034457806366c89a13146103715780637d1108f5146103de5780638373ae71146104845780638d5802e21461049b575b600080fd5b3480156100cb57600080fd5b5061010260048036038101908080359060200190929190803590602001908201803590602001919091929391929390505050610508565b005b34801561011057600080fd5b50610175600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190820180359060200191909192939192939080359060200190820180359060200191909192939192939050505061069f565b6040518082815260200191505060405180910390f35b34801561019757600080fd5b506101a0610d76565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156101e35780820151818401526020810190506101c8565b505050509050019250505060405180910390f35b34801561020357600080fd5b50610250600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001919091929391929390505050610e0e565b005b34801561025e57600080fd5b5061027d600480360381019080803590602001909291905050506110d6565b005b34801561028b57600080fd5b506102aa60048036038101908080359060200190929190505050611317565b005b3480156102b857600080fd5b506102ed600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506115f5565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b83811015610330578082015181840152602081019050610315565b505050509050019250505060405180910390f35b34801561035057600080fd5b5061036f6004803603810190808035906020019092919050505061168f565b005b34801561037d57600080fd5b5061039c60048036038101908080359060200190929190505050611de6565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103ea57600080fd5b5061040960048036038101908080359060200190929190505050611e30565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561044957808201518184015260208101905061042e565b50505050905090810190601f1680156104765780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561049057600080fd5b50610499611ef2565b005b3480156104a757600080fd5b506104c66004803603810190808035906020019092919050505061210b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3373ffffffffffffffffffffffffffffffffffffffff1660018481548110151561052e57fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610611576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260288152602001807f4f6e6c79206173736574206f776e65722063616e20736574206120636c61696d81526020017f20737472696e672e00000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b818160018581548110151561062257fe5b90600052602060002090600402016003019190610640929190612670565b507fb43a5e5b9bd1ea2d74f7665958cacc3ca8045ad56650699d26a27b5874c13e6883838360405180848152602001806020018281038252848482818152602001925080828437820191505094505050505060405180910390a1505050565b6000806000600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101541415156107a8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260448152602001807f596f7520616c72656164792068617665206f6e65207472616465206f6666657281526020017f2e2043616e63656c20697420666972737420746f206d616b652061206e65772081526020017f6f6e652e0000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b600090505b858590508110156108e2573373ffffffffffffffffffffffffffffffffffffffff16600187878481811015156107df57fe5b905060200201358154811015156107f257fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156108d5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260348152602001807f596f7520617474656d7074656420746f207472616465206974656d287329207781526020017f6869636820796f7520646f206e6f74206f776e2e00000000000000000000000081525060400191505060405180910390fd5b80806001019150506107ad565b600090505b83839050811015610a42578673ffffffffffffffffffffffffffffffffffffffff166001858584818110151561091957fe5b9050602002013581548110151561092c57fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610a35576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260418152602001807f596f7520617474656d7074656420746f2072657175657374206974656d28732981526020017f20776869636820796f757220706172746e657220646f6573206e6f74206f776e81526020017f2e0000000000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b80806001019150506108e7565b600060a0604051908101604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018973ffffffffffffffffffffffffffffffffffffffff1681526020018888808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050508152602001868680806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050815260200160006003811115610aff57fe5b8152509080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190610bd79291906126f0565b506060820151816003019080519060200190610bf49291906126f0565b5060808201518160040160006101000a81548160ff02191690836003811115610c1957fe5b0217905550505050600160008054905003600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055507fc788dc957ce3b2a800116bdadc89cc7672003de005574c1f3a93b85c68dbf6d0600160008054905003338989898989604051808881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001806020018381038352878782818152602001925060200280828437820191505083810382528585828181526020019250602002808284378201915050995050505050505050505060405180910390a160016000805490500391505095945050505050565b6060600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805480602002602001604051908101604052809291908181526020018280548015610e0457602002820191906000526020600020905b815481526020019060010190808311610df0575b5050505050905090565b60016080604051908101604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff16815260200184848080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050815260200160206040519081016040528060008152508152509080600181540180825580915050906001820390600052602060002090600402016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040820151816002019080519060200190610f7592919061273d565b506060820151816003019080519060200190610f9292919061273d565b50505050600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001600180805490500390806001815401808255809150509060018203906000526020600020016000909192909190915055507fde83d584d6c13847c0a3a4aa6147b7f36268ec75ff0765a20adc8d372ff1c34b600180805490500384338585604051808681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018060200182810382528484828181526020019250808284378201915050965050505050505060405180910390a1505050565b3373ffffffffffffffffffffffffffffffffffffffff166001828154811015156110fc57fe5b906000526020600020906004020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611205576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260418152602001807f496e206f7264657220746f206275726e20616e2061737365742c20796f75206e81526020017f65656420746f20626520746865206f6e652077686f20656d697474656420697481526020017f2e0000000000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b61124f60018281548110151561121757fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1682612155565b60018181548110151561125e57fe5b9060005260206000209060040201600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006112cb91906127bd565b6003820160006112db91906127bd565b50507fc9df5636779f4b2aa9fbd4a41fffe4e900e59ceb626ed404190ff49b188848ca816040518082815260200191505060405180910390a150565b3373ffffffffffffffffffffffffffffffffffffffff1660008281548110151561133d57fe5b906000526020600020906005020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611420576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f596f7520617265206e6f742074686520726563697069656e74206f662067697681526020017f656e207472616465206f666665722e000000000000000000000000000000000081525060400191505060405180910390fd5b6000600381111561142d57fe5b60008281548110151561143c57fe5b906000526020600020906005020160040160009054906101000a900460ff16600381111561146657fe5b1415156114db576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807f54686973206f66666572206973206e6f742070656e64696e672e00000000000081525060200191505060405180910390fd5b60036000828154811015156114ec57fe5b906000526020600020906005020160040160006101000a81548160ff0219169083600381111561151857fe5b0217905550600060026000808481548110151561153157fe5b906000526020600020906005020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055507fa0f55cae4a69c1cd925b9b639fa1639377d7097df0e253e7106c96511f8a0359816003604051808381526020018260038111156115de57fe5b60ff1681526020019250505060405180910390a150565b6060600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000180548060200260200160405190810160405280929190818152602001828054801561168357602002820191906000526020600020905b81548152602001906001019080831161166f575b50505050509050919050565b60003373ffffffffffffffffffffffffffffffffffffffff166000838154811015156116b757fe5b906000526020600020906005020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561179a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602f8152602001807f596f7520617265206e6f742074686520726563697069656e74206f662067697681526020017f656e207472616465206f666665722e000000000000000000000000000000000081525060400191505060405180910390fd5b600060038111156117a757fe5b6000838154811015156117b657fe5b906000526020600020906005020160040160009054906101000a900460ff1660038111156117e057fe5b141515611855576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807f54686973206f66666572206973206e6f742070656e64696e672e00000000000081525060200191505060405180910390fd5b600090505b60008281548110151561186957fe5b906000526020600020906005020160020180549050811015611a125760008281548110151561189457fe5b906000526020600020906005020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660016000848154811015156118ed57fe5b90600052602060002090600502016002018381548110151561190b57fe5b906000526020600020015481548110151561192257fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611a05576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252602c8152602001807f4f666665722073656e646572206e6f206c6f6e676572206f776e73206d656e7481526020017f696f6e6564206974656d732e000000000000000000000000000000000000000081525060400191505060405180910390fd5b808060010191505061185a565b600090505b600082815481101515611a2657fe5b906000526020600020906005020160030180549050811015611b8f573373ffffffffffffffffffffffffffffffffffffffff166001600084815481101515611a6a57fe5b906000526020600020906005020160030183815481101515611a8857fe5b9060005260206000200154815481101515611a9f57fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611b82576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f596f75206e6f206c6f6e676572206f776e206d656e74696f6e6564206974656d81526020017f732e00000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b8080600101915050611a17565b600090505b600082815481101515611ba357fe5b906000526020600020906005020160020180549050811015611c0d57611c00600083815481101515611bd157fe5b906000526020600020906005020160020182815481101515611bef57fe5b906000526020600020015433612433565b8080600101915050611b94565b600090505b600082815481101515611c2157fe5b906000526020600020906005020160030180549050811015611ccb57611cbe600083815481101515611c4f57fe5b906000526020600020906005020160030182815481101515611c6d57fe5b9060005260206000200154600084815481101515611c8757fe5b906000526020600020906005020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16612433565b8080600101915050611c12565b6002600083815481101515611cdc57fe5b906000526020600020906005020160040160006101000a81548160ff02191690836003811115611d0857fe5b02179055506000600260008085815481101515611d2157fe5b906000526020600020906005020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055507fa0f55cae4a69c1cd925b9b639fa1639377d7097df0e253e7106c96511f8a035982600260405180838152602001826003811115611dce57fe5b60ff1681526020019250505060405180910390a15050565b6000600182815481101515611df757fe5b906000526020600020906004020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6060600182815481101515611e4157fe5b90600052602060002090600402016002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015611ee65780601f10611ebb57610100808354040283529160200191611ee6565b820191906000526020600020905b815481529060010190602001808311611ec957829003601f168201915b50505050509050919050565b6000600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001015414151515611fad576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f596f752068617665206e6f2070656e64696e67207472616465206f666665722e81525060200191505060405180910390fd5b60016000600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001015481548110151561200057fe5b906000526020600020906005020160040160006101000a81548160ff0219169083600381111561202c57fe5b02179055507fa0f55cae4a69c1cd925b9b639fa1639377d7097df0e253e7106c96511f8a0359600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101546001604051808381526020018260038111156120ad57fe5b60ff1681526020019250505060405180910390a16000600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010181905550565b600060018281548110151561211c57fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600080600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805490509150600090505b600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001805490508110156122685782600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018281548110151561224257fe5b9060005260206000200154141561225b57809150612268565b80806001019150506121a5565b600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000180549050821015156122bb5761242d565b8190505b6001600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000180549050038110156123d657600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016001820181548110151561236157fe5b9060005260206000200154600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001828154811015156123bb57fe5b906000526020600020018190555080806001019150506122bf565b600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000180548091906001900361242b9190612805565b505b50505050565b7fd3823028b5319e3ee5a45cc847c9c6b651e07ed005b0970b0a6867c8b512a3e78260018481548110151561246457fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001935050505060405180910390a161255c60018381548110151561252457fe5b906000526020600020906004020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683612155565b8060018381548110151561256c57fe5b906000526020600020906004020160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060206040519081016040528060008152506001838154811015156125db57fe5b906000526020600020906004020160030190805190602001906125ff929190612831565b50600260008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018290806001815401808255809150509060018203906000526020600020016000909192909190915055505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106126b157803560ff19168380011785556126df565b828001600101855582156126df579182015b828111156126de5782358255916020019190600101906126c3565b5b5090506126ec91906128b1565b5090565b82805482825590600052602060002090810192821561272c579160200282015b8281111561272b578251825591602001919060010190612710565b5b50905061273991906128b1565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061277e57805160ff19168380011785556127ac565b828001600101855582156127ac579182015b828111156127ab578251825591602001919060010190612790565b5b5090506127b991906128b1565b5090565b50805460018160011615610100020316600290046000825580601f106127e35750612802565b601f01602090049060005260206000209081019061280191906128b1565b5b50565b81548183558181111561282c5781836000526020600020918201910161282b91906128b1565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061287257805160ff19168380011785556128a0565b828001600101855582156128a0579182015b8281111561289f578251825591602001919060010190612884565b5b5090506128ad91906128b1565b5090565b6128d391905b808211156128cf5760008160009055506001016128b7565b5090565b905600a165627a7a723058208fbb5e1f6e319b13e706447fc700f517add78582742c3e65982994a99d2daa660029";

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
