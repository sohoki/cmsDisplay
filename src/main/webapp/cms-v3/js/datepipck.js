// ui datepicker script 부분

    $(function () {
        var clareCalendar = {
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            weekHeader: 'Wk',
            dateFormat: 'yymmdd', //형식(20120303)
            autoSize: false, //오토리사이즈(body등 상위태그의 설정에 따른다)
            changeMonth: true, //월변경가능
            changeYear: true, //년변경가능
            showMonthAfterYear: true, //년 뒤에 월 표시
            //buttonImageOnly: true, //이미지표시
           // buttonText: '달력--선택하세요--', //버튼 텍스트 표시
            //buttonImage: '/images/calendar.gif', //이미지주소
            showOn: "both", //엘리먼트와 이미지 동시 사용(both,button)
            yearRange: '1970:2099' //1990년부터 2020년까지
        };        
        $("#schStartDay").datepicker(clareCalendar);
        $("#schEndDay").datepicker(clareCalendar);
        //$("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
        $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
    }); 



// ui datepicker body 부분