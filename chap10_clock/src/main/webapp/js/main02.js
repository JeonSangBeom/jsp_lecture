$.ajax({
	url: "ClockJsonList.do",
	//dataType: "json", //넘어오는 데이터를 json으로 처리하겠다. / 안쓰면 html 로 넘어져 온다(콘솔창에) / jquery가 json으로 바꿔 주는 것
	//사용하지 않을 경우 jsp 에서 받아오는 것을 contentType=application/json;으로 받기
	success: function(data) {
		console.log(data); // 데이터 넘어오는지 확인
		const clockList = data.clockList;// DATA의 clocklist받아오기
		console.log(clockList);
		let output = "";
		$.each(clockList, function(i,item) {
			output += `<div class="section" id="" style="background-image:url('upload_clock/${item.clockRealImg}')">
				<div class="info">
					<p class="category" data-splitting>${item.category}</p>
					<p class="title" data-splitting>${item.title}</p>
					<p class="depth" data-splitting>${item.depth}</p>
					<p class="price" data-splitting>${item.price}</p>
				</div>
			</div>`
		});		
		$("#main").html(output);
		const fp = new fullpage("#main", {
			scrollBar: true,
			onLeave: function(original, destination, index) {
				//console.log(destination);
				moveChar(`.section:nth-child(${destination.index + 1}) .char`);
			}
		});
		Splitting();
		moveChar(`.section:nth-child(1) .char`);
		function moveChar(char) {
			gsap.from(char, {
				y: -200,
				opacity: 0,
				ease: "bounce",
				duration: 1.5,
				delay: 0.5,
				stagger: {
					amount: 1,
					from: "random"
				}
			});
		}
	}
})




