/*区域级联控件*/
	var Area = {
	    _temp: {
	        province: null,
	        city: null,
	        area: null,
	        data: null
	    },
	    Init: function (provinceid, cityid, areaid, fun) {
	        var _this = Area;

	        _this._temp.province = "#" + provinceid;
	        $(_this._temp.province).combobox({
	            data: [{ "selected": true, "areaNo": "", "areaName": "全部" }],
	            valueField: "areaNo",
	            textField: "areaName",
	            onSelect: _this.ProvinceSelect,
	            width: "193px"
	        });
	        _this._temp.city = "#" + cityid;
	        $(_this._temp.city).combobox({
	            data: [{ "selected": true, "areaNo": "", "areaName": "全部" }],
	            valueField: "areaNo",
	            textField: "areaName",
	            onSelect: _this.CitySelect,
	            width: "193px"
	        });
	        _this._temp.area = "#" + areaid;
	        $(_this._temp.area).combobox({
	            data: [{ "selected": true, "areaNo": "", "areaName": "全部" }],
	            valueField: "areaNo",
	            textField: "areaName",
	            onSelect: _this.AreaSelect,
	            width: "193px"
	        });

	        $.ajax({
	            type: 'post',
	            cache: false,
	            dataType: 'json',
	            url: "../area/getAllArea.do",
	            async: true,
	            success: function (result) {
	                _this._temp.data = result;
	                var provinceList = new Array();
	                provinceList.push({ selected: true, areaNo: "", areaName: "全部" });
	                $.each(result, function (i, n) {
	                    if (n.areaLevel == 1) {
	                        provinceList.push(n);
	                    }
	                });
	                $(_this._temp.province).combobox('loadData', provinceList);
	                if (fun) {
	                    fun();
	                }
	            },
	            error: function () {
	            },
	            complete: function () {
	            }
	        });
	    },
	    ProvinceSelect: function (item) {
	        var _this = Area,
	            cityList = new Array();

	        cityList.push({ selected: true, areaNo: "", areaName: "全部" });
	        $.each(_this._temp.data, function (i, n) {
	            if (n.parentNo == item.areaNo) {
	                cityList.push(n);
	            }
	        });

	        $(_this._temp.city).combobox("loadData", cityList);
	        $(_this._temp.area).combobox("loadData", [{ selected: true, areaNo: "", areaName: "全部" }]);
	    },
	    CitySelect: function (item) {
	        var _this = Area,
	            areaList = new Array();

	        areaList.push({ selected: true, areaNo: "", areaName: "全部" });
	        $.each(_this._temp.data, function (i, n) {
	            if (n.parentNo == item.areaNo) {
	                areaList.push(n);
	            }
	        });

	        $(_this._temp.area).combobox("loadData", areaList);
	    }
	};