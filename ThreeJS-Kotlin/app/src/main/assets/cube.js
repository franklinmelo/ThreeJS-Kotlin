var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera( 75, window.innerWidth/window.innerHeight, 0.1, 1000 );

var renderer = new THREE.WebGLRenderer({ alpha: true }  );
renderer.setSize( window.innerWidth, window.innerHeight );
renderer.setClearColor( 0xffffff, 0 );
document.body.appendChild( renderer.domElement );

var geometry = new THREE.BoxGeometry( 5, 5, 5 );
var cubeMaterials = [
                     new THREE.MeshBasicMaterial({color:0xff0000, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0x00ff00, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0x0000ff, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0xffff00, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0xff00ff, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     new THREE.MeshBasicMaterial({color:0x00ffff, transparent:true, opacity:0.8, side: THREE.DoubleSide}),
                     ];
 var cube = new THREE.Mesh( geometry, cubeMaterials );

 var geometry = new THREE.SphereGeometry( 3, 32, 32 );
 var material = new THREE.MeshBasicMaterial( {color: 0xff00ff, wireframe: true} );
 var sphere = new THREE.Mesh( geometry, material );

 cube.position.set(4,0,0)
 sphere.position.set(-10,0,0)

 var group = new THREE.Group();
 group.add( cube );
 group.add( sphere );

 scene.add( group );

 camera.position.z = 10;

 var animate = function () {
     requestAnimationFrame( animate );

     cube.rotation.x += 0.01;
     cube.rotation.y += 0.01;

     sphere.rotation.x += 0.01;
     sphere.rotation.y += 0.01;

     renderer.render( scene, camera );
 };
animate();

function renderText(text){
    var loader = new THREE.FontLoader();
    loader.load( 'https://cdn.rawgit.com/mrdoob/three.js/master/examples/fonts/helvetiker_regular.typeface.json', function ( font ) {

                var geometry = new THREE.TextGeometry( text, {
                                                      font: font,
                                                      size: 1,
                                                      height: 0.1,
                                                      curveSegments: 10,
                                                      bevelSize: 0,
                                                      bevelOffset: 0,
                                                      bevelSegments: 0
                                                      } );
                var material = new THREE.MeshBasicMaterial({ color: 0xff0000 });
                var cube = new THREE.Mesh(geometry, material);
                if(group.children[2] != null){
                    group.children.pop()
                }
                group.add(cube)
                group.children[2].position.set(-6,4,0)
                scene.add(group)

                } );
}