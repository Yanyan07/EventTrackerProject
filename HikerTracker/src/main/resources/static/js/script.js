window.addEventListener('load', function(e){
	console.log('script.js loaded');
	init();
});

function init(){
	document.hikersForm.findAll.addEventListener('click',function(event){
		event.preventDefault();
		getAllHikers();
	});

	document.createForm.addHike.addEventListener('click',function(event){
		event.preventDefault();
		let cf = document.createForm;
		let newHike = {
			hikerId: cf.hikerId.value,
			trailId: cf.trailId.value,
			distance: cf.distance.value,
			hikingDate: cf.hikeDate.value
		};
		postNewHike(newHike);
	});
}

//hikings/{hikerId}/{trailId}/hiking
function postNewHike(newHike){
	let xhr = new XMLHttpRequest();
	xhr.open('POST', `api/hikings/${newHike.hikerId}/${newHike.trailId}/hiking`);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status===200 || xhr.status===201){
				let hike = JSON.parse(xhr.responseText);
				// getAllHikeActivities(newHike.hikerId);
				getAllHikeActivities(hike.hiker.id);
			}else{
				let create = document.getElementById('createData');
				create.textContent = 'Fail to add new hike activity!';
			}
		}
	};
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(newHike));
}


function getAllHikers(){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/hikers');
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let hikers = JSON.parse(xhr.responseText);
				displayHikers(hikers);
			}else{
				let hikersData = document.getElementById('hikersData');
				hikersData.textContent = 'No hikers found!';
			}
		}
	}
	xhr.send(null);
}

function displayHikers(hikers){
	let hikersData = document.getElementById('hikersData');
	hikersData.textContent = '';
	let table = document.createElement('table');
	let thead = document.createElement('thead');
	hikersData.appendChild(table);
	table.appendChild(thead);
	let tr = document.createElement('tr');
	thead.appendChild(tr);
	let th = document.createElement('th');
	th.textContent = 'hikerId';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Name';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Gender';
	tr.appendChild(th);

	let tbody = document.createElement('tbody');
	table.appendChild(tbody);
	for(let hiker of hikers){
		tr = document.createElement('tr');
		tbody.appendChild(tr);
		let td = document.createElement('td');
		td.textContent = hiker.id;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hiker.name;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hiker.gender;
		tr.appendChild(td);

		let button = document.createElement('button');
		button.textContent = 'All Hike Details';
		tr.appendChild(button);
		button.addEventListener('click',function(event){
			event.preventDefault();
			getAllHikeActivities(hiker.id);
		});

		button = document.createElement('button');
		button.textContent = 'Total Distance';
		tr.appendChild(button);
		button.addEventListener('click', function(event){
			event.preventDefault();
			getHikeDistance(hiker);
		});

		button = document.createElement('button');
		button.textContent = 'Favorite Trail';
		tr.appendChild(button);
		button.addEventListener('click', function(event){
			event.preventDefault();
			getFavoriteTrail(hiker);
		});
	}
}
//hikers/distance/{hikerId}
function getHikeDistance(hiker){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/hikers/distance/'+hiker.id);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			let distanceData = document.getElementById('distanceData');
			if(xhr.status === 200){
				let distance = JSON.parse(xhr.responseText);
				distanceData.textContent = `The totol hike distance for ${hiker.name} is ${distance} miles.`;
			}else{	
				distanceData.textContent = 'No distance found!';
			}
		}
	}
	xhr.send(null);
}

//hikers/favorite/{hikerId}
function getFavoriteTrail(hiker){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/hikers/favorite/'+hiker.id);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			let favoriteData = document.getElementById('favoriteData');
			favoriteData.textContent = '';
			if(xhr.status === 200){
				let favorite = JSON.parse(xhr.responseText);
				favoriteData.textContent = `The favorite trail of ${hiker.name} is ${favorite.name}.(decided by hike times, then hike distance)`;
			}else{	
				favoriteData.textContent = 'No favorite trail found!';
			}
		}
	}
	xhr.send(null);
}
//hikings/{hikerId}
function getAllHikeActivities(hikerId){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/hikings/'+hikerId);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let hikeItems = JSON.parse(xhr.responseText);
				displayHikeActivities(hikeItems);
			}else{
				let hikeData = document.getElementById('hikeData');
				hikeData.textContent = 'No hike found!';
			}
		}
	}
	xhr.send(null);
}

function displayHikeActivities(hikeItems){
	let hikeData = document.getElementById('hikeData');
	let thead = document.getElementById('hikeThead');
	while(thead.firstElementChild){
		thead.removeChild(thead.firstElementChild);
	}
	let tbody = document.getElementById('hikeTbody');
	while(tbody.firstElementChild){
		tbody.removeChild(tbody.firstElementChild);
	}

	let tr = document.createElement('tr');
	thead.appendChild(tr);
	let th = document.createElement('th');
	th.textContent = 'hikeId';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Distance';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'Hike Date';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'HikerId';
	tr.appendChild(th);
	th = document.createElement('th');
	th.textContent = 'TrailId';
	tr.appendChild(th);

	for(let hike of hikeItems){
		tr = document.createElement('tr');
		let td = document.createElement('td');
		td.textContent = hike.id;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.distance;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.hikingDate;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.hiker.id;
		tr.appendChild(td);
		td = document.createElement('td');
		td.textContent = hike.trail.id;
		tr.appendChild(td);
		tbody.appendChild(tr);

		let button = document.createElement('button');
		button.textContent = 'Delete';
		tr.appendChild(button);
		button.addEventListener('click',function(event){
			event.preventDefault();
			deleteHikeActivity(hike);
		});

		button = document.createElement('button');
		button.textContent = 'Update';
		tr.appendChild(button);
		button.addEventListener('click',function(event){
			event.preventDefault();
			createUpdateForm();

			let button = document.getElementById('updateButton');
			let form = document.getElementById('updateForm');
			if(button){
				button.addEventListener('click', function(event){
				event.preventDefault();
				let updateHike = {
					hikerId: form.hikerId.value,
					trailId: form.trailId.value,
					hikeId: form.hikeId.value,
					distance: form.distance.value,
					hikingDate: form.hikeDate.value 
				};
				updateHikeActivity(updateHike);
				});
			}
		});

	}
}

function deleteHikeActivity(hike){
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', `api/hikings/${hike.hiker.id}/${hike.id}/hiking`);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status===200 || xhr.status===204){
				//let jhike = JSON.parse(xhr.responseText);
				getAllHikeActivities(hike.hiker.id);
			}else{
				let deleted = document.getElementById('deleteData');
				deleted.textContent = 'Fail to delete hike activity!';
			}
		}
	};
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(hike));
}

function createUpdateForm(){
	let form = document.getElementById('updateForm');
	while(form.firstElementChild){
		form.removeChild(form.firstElementChild);
	}
	let input = document.createElement('input');
	input.setAttribute('type', 'number');
	input.setAttribute('name', 'hikerId');
	input.setAttribute('placeholder', 'Hiker Id');
	form.appendChild(input);
	let br = document.createElement('br');
	form.appendChild(br);
	input = document.createElement('input');
	input.setAttribute('type', 'number');
	input.setAttribute('name', 'trailId');
	input.setAttribute('placeholder', 'Trail Id');
	form.appendChild(input);
	br = document.createElement('br');
	form.appendChild(br);
	input = document.createElement('input');
	input.setAttribute('type', 'number');
	input.setAttribute('name', 'hikeId');
	input.setAttribute('placeholder', 'Hike Id');
	form.appendChild(input);
	br = document.createElement('br');
	form.appendChild(br);
	input = document.createElement('input');
	input.setAttribute('type', 'text');
	input.setAttribute('name', 'distance');
	input.setAttribute('placeholder', 'Distance');
	form.appendChild(input);
	br = document.createElement('br');
	form.appendChild(br);
	input = document.createElement('input');
	input.setAttribute('type', 'date');
	input.setAttribute('name', 'hikeDate');
	input.setAttribute('placeholder', 'Hike Date');
	form.appendChild(input);
	br = document.createElement('br');
	form.appendChild(br);
	button = document.createElement('button');
	button.setAttribute('type', 'button');
	button.setAttribute('id', 'updateButton');
	button.textContent = 'Update Hike';
	form.appendChild(button);

}

//hikings/{hikerId}/{trailId}/{hikingId}/hiking
function updateHikeActivity(updateHike){
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', `api/hikings/${updateHike.hikerId}/${updateHike.trailId}/${updateHike.hikeId}/hiking`);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status===200 || xhr.status===201){
				let hike = JSON.parse(xhr.responseText);
				getAllHikeActivities(hike.hiker.id);
			}else{
				let update = document.getElementById('updateData');
				update.textContent = 'Fail to update hike activity!';
			}
		}
	};
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.send(JSON.stringify(updateHike));
}

